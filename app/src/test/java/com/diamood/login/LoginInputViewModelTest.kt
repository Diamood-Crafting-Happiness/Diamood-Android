package com.diamood.login

import com.diamood.data.login.Country
import com.diamood.viewmodels.login.LoginInputViewModel
import org.junit.Test
import kotlin.math.roundToInt

class LoginInputViewModelTest {

    private val viewModel = LoginInputViewModel()

    @Test
    fun `when country is selected, then assign country`() {
        val country = Country("+93", "🇦🇫", "Afghanistan", "AF")

        viewModel.onCountrySelected(country)

        assert(viewModel.prefix == country)
    }

    @Test
    fun `when text is changed, then assign phone`() {
        val number = "600 000 000"

        viewModel.onPhoneChanged(number)

        assert(viewModel.number == number)
    }

    @Test
    fun `when sms code is changed, then assign smsCode`() {
        val number = Math.random().roundToInt().toString().take(6)

        viewModel.onSMSChanged(number)

        assert(viewModel.smsCode.value == number)
    }

    @Test
    fun `when send sms button is pressed, then state changes to loading`() {
        viewModel.onSendSMSClicked()

        assert(viewModel.isLoading)
    }
}
