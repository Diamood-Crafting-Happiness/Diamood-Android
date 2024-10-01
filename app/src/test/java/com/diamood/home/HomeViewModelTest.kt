package com.diamood.home

import com.diamood.api.home.HomeRepositoryFakeImpl
import com.diamood.data.main.routes.Routes
import com.diamood.viewmodels.home.HomeViewModel
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `when user is not logged, then route is tutorial`() {
        val repository = HomeRepositoryFakeImpl(false)

        val viewModel = HomeViewModel(repository)

        assert(viewModel.route.value == Routes.TutorialRoute)
    }

    @Test
    fun `when user is logged, then route is logged`() {
        val repository = HomeRepositoryFakeImpl(true)

        val viewModel = HomeViewModel(repository)

        assert(viewModel.route.value == Routes.LoggedHomeRoute)
    }
}
