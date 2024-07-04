package com.diamood.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diamood.ui.home.buttons.HomeButtons
import com.diamood.ui.home.buttons.HomeDirection
import com.diamood.ui.home.title.HomeTitle
import com.diamood.ui.home.tutorial.HomeTutorial


@Composable
fun Home(navigate: (HomeDirection) -> Unit) {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
        HomeTitle()
        HomeTutorial()
        HomeButtons(navigate)
    }
}

@Preview
@Composable
fun HomePreview() {
    Home {}
}
