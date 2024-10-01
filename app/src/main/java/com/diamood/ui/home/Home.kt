package com.diamood.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diamood.api.home.HomeRepositoryFakeImpl
import com.diamood.data.main.routes.Routes.LoggedHomeRoute
import com.diamood.data.main.routes.Routes.TutorialRoute
import com.diamood.ui.home.buttons.HomeButtons
import com.diamood.ui.home.buttons.HomeDirection
import com.diamood.ui.home.logged.HomeLogged
import com.diamood.ui.home.title.HomeTitle
import com.diamood.ui.home.tutorial.HomeTutorial
import com.diamood.viewmodels.home.HomeViewModel


@Composable
fun Home(viewModel: HomeViewModel = hiltViewModel(), navigate: (HomeDirection) -> Unit) {
    val navController = rememberNavController()
    val route = viewModel.routeValue

    NavHost(navController, startDestination = route, modifier = Modifier) {
        composable<TutorialRoute>(typeMap = TutorialRoute.typeMap) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                HomeTitle()
                HomeTutorial()
                HomeButtons(navigate)
            }
        }

        composable<LoggedHomeRoute>(typeMap = LoggedHomeRoute.typeMap) {
            HomeLogged(navController = navController)
        }
    }
}

@Preview
@Composable
fun HomeTutorialPreview() {
    Home(viewModel = HomeViewModel(HomeRepositoryFakeImpl(false))) {}
}

@Preview
@Composable
fun HomeLoggedPreview() {
    Home(viewModel = HomeViewModel(HomeRepositoryFakeImpl(true))) {}
}
