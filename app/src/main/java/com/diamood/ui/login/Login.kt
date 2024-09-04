package com.diamood.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diamood.data.main.routes.Routes
import com.diamood.data.main.routes.Routes.CountryRoute
import com.diamood.data.main.routes.Routes.HomeRoute
import com.diamood.data.main.routes.Routes.LoginRoute
import com.diamood.theme.PrimaryLight
import com.diamood.theme.SecondaryLight
import com.diamood.ui.login.screen.CountryListScreen
import com.diamood.ui.login.screen.LoginScreen
import com.diamood.viewmodels.login.CountryListViewModel
import com.diamood.viewmodels.login.LoginInputViewModel

@Composable
fun Login(onNavigate: (Routes) -> Unit) {
    val loginInputViewModel: LoginInputViewModel = hiltViewModel()
    val countryListViewModel: CountryListViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(navController, startDestination = LoginRoute, modifier = Modifier) {
        composable<LoginRoute>(typeMap = LoginRoute.typeMap) {
            LoginScreen(loginInputViewModel) { onNavigate(HomeRoute) }
        }

        composable<CountryRoute>(typeMap = CountryRoute.typeMap) {
            CountryListScreen(countryListViewModel) { loginInputViewModel.onCountrySelected(it) }
        }
    }
}

val loginBackground = Brush.horizontalGradient(
    colors = listOf(SecondaryLight, PrimaryLight),
)

@Preview
@Composable
fun LoginPreview() {
    Login {}
}

