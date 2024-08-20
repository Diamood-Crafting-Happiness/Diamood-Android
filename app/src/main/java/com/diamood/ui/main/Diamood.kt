package com.diamood.ui.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diamood.data.main.routes.Routes.AddRoute
import com.diamood.data.main.routes.Routes.HomeRoute
import com.diamood.data.main.routes.Routes.LoginRoute
import com.diamood.data.main.routes.Routes.ShopRoute
import com.diamood.ui.home.Home
import com.diamood.ui.home.buttons.HomeDirection
import com.diamood.ui.login.Login
import com.diamood.ui.main.menu.DiamoodBottomBar
import com.diamood.ui.shop.Shop
import com.diamood.viewmodels.main.BottomBarViewModel

@Composable
fun Diamood(context: Activity?) {
    val navController = rememberNavController()
    val bottomBarViewModel: BottomBarViewModel = hiltViewModel()
    val showBottom = remember { mutableStateOf(true) }
    val bottomBarOffset by animateDpAsState(targetValue = if (showBottom.value) 0.dp else 50.dp)

    Scaffold(bottomBar = {
        if (!showBottom.value) return@Scaffold

        DiamoodBottomBar(
            navController = navController,
            viewModel = bottomBarViewModel,
            modifier = Modifier.offset(y = bottomBarOffset),
        )
    }) {

        NavHost(navController, startDestination = HomeRoute, modifier = Modifier.padding(it)) {
            composable<HomeRoute>(typeMap = HomeRoute.typeMap) {
                showBottom.value = true

                Home { direction ->
                    when (direction) {
                        HomeDirection.SHOP -> {
                            bottomBarViewModel.onClick(ShopRoute)
                            navController.navigate(ShopRoute)
                        }

                        HomeDirection.LOGIN -> {
                            navController.navigate(LoginRoute)
                        }
                    }
                }
            }

            composable<AddRoute>(typeMap = AddRoute.typeMap) {
                showBottom.value = true

                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .wrapContentSize(),
                    text = "Add",
                    textAlign = TextAlign.Center,
                )
            }

            composable<ShopRoute>(typeMap = ShopRoute.typeMap) {
                showBottom.value = true

                Shop()
            }

            composable<LoginRoute>(typeMap = LoginRoute.typeMap) {
                showBottom.value = false

                Login { route -> navController.navigate(route) }
            }
        }

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
