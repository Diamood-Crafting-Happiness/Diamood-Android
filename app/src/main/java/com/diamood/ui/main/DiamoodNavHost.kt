package com.diamood.ui.main

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diamood.data.main.routes.Routes.AddRoute
import com.diamood.data.main.routes.Routes.HomeRoute
import com.diamood.data.main.routes.Routes.ShopRoute
import com.diamood.ui.home.Home
import com.diamood.ui.home.buttons.HomeDirection
import com.diamood.ui.shop.Shop
import com.diamood.viewmodels.main.BottomBarViewModel

@Composable
fun DiamoodNavHost(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: BottomBarViewModel
) {
    NavHost(navController, startDestination = HomeRoute, modifier = modifier) {
        composable<HomeRoute>(typeMap = HomeRoute.typeMap) {
            Home {
                when (it) {
                    HomeDirection.SHOP -> {
                        viewModel.onClick(ShopRoute)
                        navController.navigate(ShopRoute)
                    }

                    HomeDirection.LOGIN -> {}
                }
            }
        }

        composable<AddRoute>(typeMap = AddRoute.typeMap) {
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
            Shop()
        }
    }
}

@Preview
@Composable
fun DiamoodNavHostPreview() {
    DiamoodNavHost(Modifier, navController = rememberNavController(), viewModel = hiltViewModel())
}
