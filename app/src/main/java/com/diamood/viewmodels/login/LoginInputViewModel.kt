package com.diamood.viewmodels.login

import androidx.lifecycle.ViewModel
import com.diamood.data.login.Country
import com.diamood.data.login.PhoneInfo
import com.diamood.viewmodels.login.LoadingState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

sealed class LoginState() {
    data object InProgress : LoginState()
    data object Completed : LoginState()
    data object SMSSent : LoginState()
}

sealed class LoadingState() {
    data object Loading : LoadingState()
    data object Loaded: LoadingState()
}

@HiltViewModel
class LoginInputViewModel @Inject internal constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<LoginState>(LoginState.InProgress)
    val uiState = _uiState.asStateFlow()

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loaded)
    val isLoading = _loadingState.asStateFlow().value == Loading

    private val _phoneInfo = MutableStateFlow(
        PhoneInfo(
            Country("España", "🇪🇸", "ES", "+34"),
            ""
        )
    )
    val phoneInfo = _phoneInfo.asStateFlow()

    val prefix get() = phoneInfo.value.country

    val number get() = phoneInfo.value.number

    fun onCountrySelected(country: Country) {
        _phoneInfo.value = _phoneInfo.value.copy(country = country)
    }

    fun onPhoneChanged(phone: String) {
        _phoneInfo.value = _phoneInfo.value.copy(number = phone)
    }

    fun onSendSMSClicked() {
        _loadingState.value = Loading
    }
}
