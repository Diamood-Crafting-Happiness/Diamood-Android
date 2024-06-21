package com.diamood.ui.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.diamood.ui.main.menu.BottomNavigationItems.Add
import com.diamood.ui.main.menu.BottomNavigationItems.Home
import com.diamood.ui.main.menu.BottomNavigationItems.Shop
import com.diamood.ui.shop.Shop
import com.diamood.viewmodels.main.BottomBarViewModel

@Composable
fun Diamood(context : Activity?) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { DiamoodBottomBar(navController = navController) }) {
        DiamoodNavHost(Modifier.padding(it), navController)

        BackHandler {
            context?.finish()
        }
    }
}

@Composable
fun DiamoodNavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(navController, startDestination = HomeRoute, modifier = modifier) {
        composable<HomeRoute>(typeMap = HomeRoute.typeMap) {
            Home()
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

@Composable
fun DiamoodBottomBar(
    viewModel: BottomBarViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val menuItems = listOf(
        Home,
        Add,
        Shop
    )

    BottomAppBar {
        NavigationBar {
            menuItems.forEach {
                NavigationBarItem(
                    selected = viewModel.selected.collectAsState().value == it.route,
                    onClick = {
                        viewModel.onClick(it.route)
                        navController.navigate(it.route)
                    },
                    icon = { Icon(imageVector = it.icon, contentDescription = it.title) },
                    label = { Text(text = it.title) },
                )
            }
        }
    }
}


@Preview
@Composable
fun DiamoodPreview() {
    Diamood(null)
}

@Preview
@Composable
fun DiamoodNavHostPreview() {
    DiamoodNavHost(Modifier, navController = rememberNavController())
}

@Preview
@Composable
fun DiamoodBottomBarPreview() {
    DiamoodBottomBar(navController = rememberNavController())
}
