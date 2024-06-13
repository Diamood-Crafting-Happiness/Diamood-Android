package com.diamood.viewmodels.main

import androidx.lifecycle.ViewModel
import com.diamood.data.main.routes.Routes
import com.diamood.data.main.routes.Routes.HomeRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject internal constructor(
): ViewModel() {

    private val _selected = MutableStateFlow<Routes>(HomeRoute)
    val selected = _selected.asStateFlow()


    fun onClick(routes: Routes) {
        _selected.value = routes
    }
}