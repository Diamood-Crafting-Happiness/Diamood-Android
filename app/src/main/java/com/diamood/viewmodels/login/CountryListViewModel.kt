package com.diamood.viewmodels.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diamood.data.login.Country
import com.diamood.domain.login.GetCountryListUseCase
import com.diamood.ui.login.LoginNavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountryListUseCase: GetCountryListUseCase
) : ViewModel() {

    private val navigationChannel = Channel<LoginNavigationEvent>()
    val navigationEventsChannelFlow = navigationChannel.receiveAsFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries = searchText.combine(_countries) { text, countries ->
        if (text.isBlank()) return@combine countries

        countries.filter { it.doesMatchQuery(text) }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5L),
        _countries.value
    )

    init {
        retrieveCountries()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun cancelSelection() {
        viewModelScope.launch {
            _searchText.value = ""
            navigationChannel.send(LoginNavigationEvent.NavigateToLogin)
        }
    }

    private fun retrieveCountries() {
        viewModelScope.launch {
            _countries.value = getCountryListUseCase()
        }
    }
}
