package com.diamood.ui.home.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diamood.theme.PrimaryLight

@Composable
fun HomeTitle() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Binvenid@ a ", fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Text(
            text = "Diamood",
            fontWeight = FontWeight.Bold,
            color = PrimaryLight,
            fontSize = 32.sp
        )
    }
}


@Preview
@Composable
fun HomeTitlePreview() {
    HomeTitle()
}
