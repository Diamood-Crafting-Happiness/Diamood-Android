package com.diamood.ui.main

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Diamood() {
    Text(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .wrapContentSize(),
        text = "Diamood",
        textAlign = TextAlign.Center,
    )
}

