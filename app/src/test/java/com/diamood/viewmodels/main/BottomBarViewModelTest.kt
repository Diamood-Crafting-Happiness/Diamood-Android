package com.diamood.viewmodels.main

import com.diamood.data.main.routes.Routes
import org.junit.Test

class BottomBarViewModelTest {
    private val viewModel = BottomBarViewModel()

    @Test
    fun `when item is clicked, then assign route to Home`(){
        val list = listOf(
            Routes.HomeRoute,
            Routes.AddRoute,
            Routes.ShopRoute
        )
        list.forEach {
            viewModel.onClick(it)

            assert(viewModel.selected.value == it)
        }
    }
}
