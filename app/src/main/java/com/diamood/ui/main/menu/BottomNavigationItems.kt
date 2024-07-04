package com.diamood.ui.main.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.diamood.data.main.routes.Routes
import com.diamood.data.main.routes.Routes.AddRoute
import com.diamood.data.main.routes.Routes.HomeRoute
import com.diamood.data.main.routes.Routes.ShopRoute

sealed class BottomNavigationItems(val icon: ImageVector, val title: String, val route: Routes) {

    data object Home : BottomNavigationItems(
        Icons.Outlined.Home,
        "Home",
        HomeRoute
    )

    data object Add : BottomNavigationItems(
        Icons.Outlined.AddCircle,
        "Añadir",
        AddRoute
    )

    data object Shop : BottomNavigationItems(
        Icons.Filled.ShoppingCart,
        "Tienda",
        ShopRoute
    )
}