package com.diamood.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diamood.theme.ButtonColors

enum class HomeDirection {
    SHOP,
    LOGIN,
}

@Composable
fun HomeButtons(navigate: (HomeDirection) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        HomeButton(text = "Ver tienda") {
            navigate(HomeDirection.SHOP)
        }
        HomeButton(text = "Iniciar sesión") {
            navigate(HomeDirection.LOGIN)
        }
        Text(
            color = Color.LightGray,
            text = "Necesitas iniciar sesión para usar el diario de Diamood."
        )
    }
}

@Composable
fun HomeButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors,
        onClick = onClick
    )
    {
        Text(text = text)
    }
}

@Preview
@Composable
fun HomeButtonsPreview() {
    HomeButtons {}
}
