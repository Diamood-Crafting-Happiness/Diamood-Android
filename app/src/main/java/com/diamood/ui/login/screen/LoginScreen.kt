package com.diamood.ui.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diamood.theme.PrimaryLight
import com.diamood.ui.login.dots.dots
import com.diamood.ui.login.form.LoginInput
import com.diamood.ui.login.loginBackground
import com.diamood.viewmodels.login.LoginInputViewModel

@Composable
fun LoginScreen(viewModel: LoginInputViewModel, onFinish: () -> Unit) {
    Column(
        modifier = Modifier
            .background(loginBackground)
            .fillMaxSize()
            .dots(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            text = "Iniciar sesión",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )
        LoginInput(viewModel)
        Text(
            text = "Cancelar",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 32.dp)
                .clickable { onFinish() },
            textAlign = TextAlign.Center,
            color = PrimaryLight,
        )
    }
}