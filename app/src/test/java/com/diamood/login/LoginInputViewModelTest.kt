package com.diamood.login

import com.diamood.MainDispatcherRule
import com.diamood.api.login.LoginRepositoryImplFake
import com.diamood.data.login.Country
import com.diamood.domain.login.SendSMSUseCase
import com.diamood.viewmodels.login.LoadingState
import com.diamood.viewmodels.login.LoadingState.Loading
import com.diamood.viewmodels.login.LoginInputViewModel
import com.diamood.viewmodels.login.LoginState
import com.diamood.viewmodels.login.LoginState.InProgress
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

class LoginInputViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val fakeRepo = LoginRepositoryImplFake()
    val useCase = SendSMSUseCase(fakeRepo)
    private val viewModel = LoginInputViewModel(useCase)

    @Test
    fun `when country is selected, then assign country`() {
        val country = Country("+93", "🇦🇫", "Afghanistan", "AF")

        viewModel.onCountrySelected(country)

        assertEquals(country, viewModel.country)
    }

    @Test
    fun `when text is changed, then assign phone`() {
        val number = "600 000 000"

        viewModel.onPhoneChanged(number)

        assertEquals(number, viewModel.number)
    }

    @Test
    fun `when sms code is changed, then assign smsCode`() {
        val number = Math.random().roundToInt().toString().take(6)

        viewModel.onSMSChanged(number)

        assertEquals(number, viewModel.smsCode.value)
    }

    @Test
    fun `when send sms button is pressed, then state changes to loading`() = runTest {
        viewModel.onSendSMSClicked()

        assertEquals(Loading, viewModel.loadingState.replayCache[0])
    }

    @Test
    fun `when send sms button is pressed, then useCase is called`() = runTest {
        viewModel.onSendSMSClicked()

        delay(30L)

        assertEquals(true, fakeRepo.smsSent)
    }

    @Test
    fun `when change number button is pressed, then state changes to in progress`() {
        viewModel.onNumberChangeClicked()

        assertEquals(InProgress,viewModel.uiState.value)
    }
}
