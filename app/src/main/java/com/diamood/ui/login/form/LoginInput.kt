package com.diamood.ui.login.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.diamood.data.login.Country
import com.diamood.data.main.routes.Routes
import com.diamood.data.main.routes.Routes.CountryRoute
import com.diamood.theme.Pink80
import com.diamood.theme.PrimaryLight
import com.diamood.theme.White30
import com.diamood.viewmodels.login.LoginInputViewModel
import com.diamood.viewmodels.login.LoginState

const val MAX_LENGTH_PHONE_NUMBER = 12

@Composable
fun LoginInput(viewModel: LoginInputViewModel, navHostController: NavHostController?) {
    val phoneInfo by viewModel.phoneInfo.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CountryPickerButton(phoneInfo.country) { navHostController?.navigate(CountryRoute) }
        PhoneInput(phoneInfo.number) { viewModel.onPhoneChanged(it) }
        when (uiState) {
            LoginState.InProgress -> SMSButton(phoneInfo.number) { viewModel.onSendSMSClicked() }
            LoginState.Loading -> LoadingSMSButton()
            LoginState.Completed -> navHostController?.navigate(Routes.CompleteLoginRoute)
        }
    }
}

@Composable
fun CountryPickerButton(country: Country, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = White),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 4.dp,
            end = 8.dp,
            bottom = 4.dp,
        )
    ) {
        Text(text = "${country.flag} ${country.phoneCode}", color = PrimaryLight)
    }
}

@Composable
fun PhoneInput(text: String, onTextChanged: (String) -> Unit) {
    var input by remember { mutableStateOf(text) }
    Column(Modifier.padding(vertical = 16.dp)) {
        Text(text = "NÚMERO DE TELÉFONO", fontSize = 8.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(White30, shape = RoundedCornerShape(8.dp)),
            value = input,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            onValueChange = { phoneNumber ->
                if (phoneNumber.length < MAX_LENGTH_PHONE_NUMBER) input = phoneNumber

                onTextChanged(phoneNumber)
            },
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)) {
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun SMSButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(disabledContainerColor = White30),
        enabled = text.length > 4
    ) {
        Text(text = "Recibir SMS")
    }
}

@Composable
fun LoadingSMSButton() {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(disabledContainerColor = White30),
        onClick = {}
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            trackColor = Pink80
        )
    }
}

@Preview
@Composable
fun CountryPickerPreview() {
    CountryPickerButton(Country("España", "🇪🇸", "ES", "+34")) {}
}

@Preview
@Composable
fun PhoneInputPreview() {
    PhoneInput("600 000 000") {}
}

@Preview
@Composable
fun SMSButtonPreview() {
    SMSButton("600 000 000") {}
}

@Preview
@Composable
fun LoadingSMSButtonPreview() {
    LoadingSMSButton()
}

@Preview
@Composable
fun LoginInputPreview() {
    LoginInput(hiltViewModel(), null)
}
