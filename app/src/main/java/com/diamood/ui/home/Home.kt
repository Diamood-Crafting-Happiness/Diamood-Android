package com.diamood.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diamood.ui.home.buttons.HomeButtons
import com.diamood.ui.home.buttons.HomeDirection
import com.diamood.ui.home.tutorial.HomeTutorial


@Composable
fun Home(navigate: (HomeDirection) -> Unit) {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title")
        }
        HomeTutorial()
        HomeButtons(navigate)
    }
}

@Preview
@Composable
fun HomePreview() {
    Home {}
}