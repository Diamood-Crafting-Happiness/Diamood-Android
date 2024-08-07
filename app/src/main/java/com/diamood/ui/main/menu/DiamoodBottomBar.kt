package com.diamood.ui.main.menu

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diamood.ui.main.menu.BottomNavigationItems.Add
import com.diamood.ui.main.menu.BottomNavigationItems.Home
import com.diamood.ui.main.menu.BottomNavigationItems.Shop
import com.diamood.viewmodels.main.BottomBarViewModel

@Composable
fun DiamoodBottomBar(
    viewModel: BottomBarViewModel,
    navController: NavHostController,
    modifier: Modifier
) {
    val menuItems = listOf(
        Home,
        Add,
        Shop
    )

    BottomAppBar(modifier = modifier) {
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
fun DiamoodBottomBarPreview() {
    DiamoodBottomBar(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
        modifier = Modifier
    )
}
