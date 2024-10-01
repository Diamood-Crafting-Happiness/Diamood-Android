package com.diamood.viewmodels.login

import androidx.lifecycle.ViewModel
import com.diamood.api.country.CountryRepository
import com.diamood.data.login.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries get() = _countries.asStateFlow().value

    init {
        retrieveCountries()
    }

    private fun retrieveCountries() {
        _countries.value = countryRepository.getCountryList()
    }
}
