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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diamood.data.login.Country
import com.diamood.theme.PrimaryLight
import com.diamood.theme.White30
import com.diamood.viewmodels.login.LoginInputViewModel

@Composable
fun LoginInput(viewModel: LoginInputViewModel) {
    val phoneInfo by viewModel.phoneInfo.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CountryPickerButton(phoneInfo.country) {}
        PhoneInput(phoneInfo.number) { viewModel.onPhoneChanged(it) }
        SMSButton(phoneInfo.number) {}
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
    Column(Modifier.padding(vertical = 16.dp)) {
        Text(text = "NÚMERO DE TELÉFONO", fontSize = 8.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(White30, shape = RoundedCornerShape(8.dp)),
            value = text,
            onValueChange = { onTextChanged(it) },
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
fun LoginInputPreview() {
    LoginInput(hiltViewModel())
}
