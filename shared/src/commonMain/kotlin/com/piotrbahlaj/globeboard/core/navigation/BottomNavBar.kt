package com.piotrbahlaj.globeboard.core.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.piotrbahlaj.globeboard.core.constants.Constants

@Composable
fun BottomNavBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        windowInsets = WindowInsets.navigationBars.only(WindowInsetsSides.Bottom)
    ) {
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute(Routes.Dashboard::class) } == true,
            onClick = {
                navController.navigate(Routes.Dashboard) {
                    popUpTo(Routes.Dashboard) { inclusive = true }
                }
            },

            icon = { Icon(Icons.Default.Home, contentDescription = Constants.DASHBOARD) },
            label = { Text(Constants.DASHBOARD) },


            )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute(Routes.ExplorerList::class) } == true,
            onClick = {
                navController.navigate(Routes.ExplorerList) {
                    popUpTo(Routes.Dashboard)
                }
            },
            icon = { Icon(Icons.Default.Search, contentDescription = Constants.EXPLORER) },
            label = { Text(Constants.EXPLORER) }
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.hasRoute(Routes.Settings::class) } == true,
            onClick = {
                navController.navigate(Routes.Settings) {
                    popUpTo(Routes.Dashboard)
                }
            },
            icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") }
        )
    }
}

fun shouldShowBottomBar(currentDestination: NavDestination?): Boolean {
    return currentDestination?.hierarchy?.any {
        it.hasRoute(Routes.Dashboard::class) || it.hasRoute(Routes.ExplorerList::class) || it.hasRoute(Routes.Settings::class)
    } == true
}