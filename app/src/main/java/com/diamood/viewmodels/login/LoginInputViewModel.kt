package com.diamood.viewmodels.login

import androidx.lifecycle.ViewModel
import com.diamood.data.login.Country
import com.diamood.data.login.PhoneInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

sealed class LoginState() {
    data object Loading : LoginState()
    data object InProgress : LoginState()
    data object Completed : LoginState()
}

@HiltViewModel
class LoginInputViewModel @Inject internal constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<LoginState>(LoginState.InProgress)
    val uiState = _uiState.asStateFlow()

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
        _uiState.value = LoginState.Loading
    }
}
