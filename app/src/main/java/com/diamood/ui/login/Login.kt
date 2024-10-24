package com.diamood.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
import kotlinx.coroutines.flow.Flow

@Composable
fun Login(onNavigate: (Routes) -> Unit) {
    val loginInputViewModel: LoginInputViewModel = hiltViewModel()
    val countryListViewModel: CountryListViewModel = hiltViewModel()
    val navController = rememberNavController()

    NavHost(navController, startDestination = LoginRoute, modifier = Modifier) {
        composable<LoginRoute>(typeMap = LoginRoute.typeMap) {
            LoginScreen(loginInputViewModel, navController) { onNavigate(HomeRoute) }
        }

        composable<CountryRoute>(typeMap = CountryRoute.typeMap) {
            ObserveAsEvents(flow = countryListViewModel.navigationEventsChannelFlow) { event ->
                when(event) {
                    is LoginNavigationEvent.NavigateToLogin -> navController.popBackStack()
                }
            }

            CountryListScreen(countryListViewModel) { country ->
                country?.let {
                    loginInputViewModel.onCountrySelected(it)
                }
            }
        }
    }
}

@Composable
private fun <T> ObserveAsEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(onEvent)
        }
    }

}

val loginBackground = Brush.horizontalGradient(
    colors = listOf(SecondaryLight, PrimaryLight),
)

sealed interface LoginNavigationEvent {
    object NavigateToLogin: LoginNavigationEvent
}

@Preview
@Composable
fun LoginPreview() {
    Login {}
}
