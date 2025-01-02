package com.diamood.login

import com.diamood.data.login.Country
import com.diamood.viewmodels.login.LoginInputViewModel
import com.diamood.viewmodels.login.LoginState
import org.junit.Test

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
    fun `when send sms button is pressed, then state changes to loading`() {
        viewModel.onSendSMSClicked()

        assert(viewModel.isLoading)
    }
}
