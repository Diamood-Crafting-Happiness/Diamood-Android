package com.diamood.ui.login.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diamood.theme.White30

@Composable
fun LoginInput() {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val text by remember { mutableStateOf("600 000 000") }

        Button(onClick = {}) {

        }
        Column(Modifier.padding(vertical = 16.dp)) {
            Text(text = "NÚMERO DE TELÉFONO", fontSize = 8.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(White30, shape = RoundedCornerShape(8.dp)),
                value = text,
                onValueChange = {},
                decorationBox = { innerTextField ->
                    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)) {
                        innerTextField()
                    }
                },
            )
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {

        }
    }
}

@Preview
@Composable
fun LoginInputPreview() {
    LoginInput()
}
