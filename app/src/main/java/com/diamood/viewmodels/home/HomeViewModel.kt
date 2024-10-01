package com.diamood.viewmodels.home

import androidx.lifecycle.ViewModel
import com.diamood.api.home.HomeRepository
import com.diamood.data.main.routes.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    homeRepository: HomeRepository
) : ViewModel() {

    private val _route = MutableStateFlow<Routes>(
        Routes.TutorialRoute
    )
    val route = _route.asStateFlow()

    init {
        _route.value = when (homeRepository.getUserLoginStatus()) {
            true -> Routes.LoggedHomeRoute
            false -> Routes.TutorialRoute
        }
    }
}
