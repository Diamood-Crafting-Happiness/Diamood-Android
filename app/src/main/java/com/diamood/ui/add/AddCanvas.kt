package com.diamood.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diamood.theme.PrimaryLight
import com.diamood.theme.SecondaryLight
import com.diamood.ui.login.dots.dots

@Composable
fun AddCanvas() {
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

    }
}

@Preview
@Composable
fun AddCanvasPreview() {
    AddCanvas()
}
