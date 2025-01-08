package com.diamood.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.diamood.data.main.routes.Routes
import com.diamood.theme.PrimaryLight
import com.diamood.theme.SecondaryLight
import com.diamood.ui.login.dots.dots
import com.diamood.viewmodels.add.AddCanvasViewModel

@Composable
fun AddCanvas(onNavigate: (Routes) -> Unit) {
    val addCanvasViewModel: AddCanvasViewModel = hiltViewModel()
    val available by addCanvasViewModel.available.collectAsStateWithLifecycle()

    val addCanvasBackground = Brush.linearGradient(
        colors = listOf(SecondaryLight, PrimaryLight),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    )
    Column(
        modifier = Modifier
            .padding(top = 36.dp)
            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            .background(addCanvasBackground)
            .dots()
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cancelar",
                fontSize = 18.sp,
                modifier = Modifier
                    .clickable { onNavigate(Routes.HomeRoute) },
                textAlign = TextAlign.Center,
                color = Color.Black,
            )
            val alpha = if (available) 1f else 0.38f
            Text(
                text = "Guardar",
                fontSize = 18.sp,
                modifier = Modifier
                    .alpha(alpha)
                    .clickable(enabled = available) {

                    },
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
        }

    }
}

@Preview
@Composable
fun AddCanvasPreview() {
    AddCanvas {}
}
