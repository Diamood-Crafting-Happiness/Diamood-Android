package com.diamood.viewmodels.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diamood.data.login.Country
import com.diamood.data.login.PhoneInfo
import com.diamood.domain.login.Result
import com.diamood.domain.login.SendSMSUseCase
import com.diamood.ui.login.LoginNavigationEvent
import com.diamood.viewmodels.login.LoadingState.Loaded
import com.diamood.viewmodels.login.LoadingState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState() {
    data object InProgress : LoginState()
    data object Completed : LoginState()
    data object SMSSent : LoginState()
}

sealed class LoadingState() {
    data object Loading : LoadingState()
    data object Loaded : LoadingState()
}

@HiltViewModel
class LoginInputViewModel @Inject internal constructor(
    val sendSMSUseCase: SendSMSUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginState>(LoginState.InProgress)
    val uiState = _uiState.asStateFlow()

    private val _loadingState = MutableStateFlow<LoadingState>(Loaded)
    val loadingState get() = _loadingState.asStateFlow()

    private val _phoneInfo = MutableStateFlow(
        PhoneInfo(
            Country("España", "🇪🇸", "ES", "+34"),
            ""
        )
    )
    val phoneInfo = _phoneInfo.asStateFlow()

    private val _smsCode = MutableStateFlow("")
    val smsCode = _smsCode.asStateFlow()

    val country get() = phoneInfo.value.country
    val prefix get() = phoneInfo.value.country.phoneCode ?: "+34"

    val number get() = phoneInfo.value.number

    fun onCountrySelected(country: Country) {
        _phoneInfo.value = _phoneInfo.value.copy(country = country)
    }

    fun onPhoneChanged(phone: String) {
        _phoneInfo.value = _phoneInfo.value.copy(number = phone)
    }

    fun onSMSChanged(code: String) {
        _smsCode.value = code
    }

    fun onSendSMSClicked() {
        _loadingState.value = Loading
        viewModelScope.launch {
            val result = sendSMSUseCase(number)
            when (result) {
                Result.Success -> _uiState.value = LoginState.SMSSent
                Result.Failure -> _uiState.value = LoginState.InProgress
            }
            _loadingState.value = Loaded
        }
    }

    fun onNumberChangeClicked() {
        _uiState.value = LoginState.InProgress
    }
}
