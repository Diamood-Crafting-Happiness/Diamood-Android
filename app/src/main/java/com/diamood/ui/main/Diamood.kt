package com.diamood.ui.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.diamood.ui.main.menu.DiamoodBottomBar
import com.diamood.viewmodels.main.BottomBarViewModel

@Composable
fun Diamood(context: Activity?) {
    val navController = rememberNavController()
    val bottomBarViewModel: BottomBarViewModel = hiltViewModel()
    Scaffold(bottomBar = {
        DiamoodBottomBar(
            navController = navController,
            viewModel = bottomBarViewModel
        )
    }) {
        DiamoodNavHost(Modifier.padding(it), navController, bottomBarViewModel)

        BackHandler {
            context?.finish()
        }
    }
}


@Preview
@Composable
fun DiamoodPreview() {
    Diamood(null)
}
