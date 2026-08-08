package com.piotrbahlaj.globeboard

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.piotrbahlaj.globeboard.core.navigation.Routes

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Routes.Dashboard
        ) {
            composable<Routes.Dashboard> {
                // DashboardScreen
            }
            composable<Routes.ExplorerList> {
                // ExplorerListScreen
            }
            composable<Routes.CountryDetail> { backStackEntry ->
                val args: Routes.CountryDetail = backStackEntry.toRoute()
                // CountryDetailScreen(isoCode = args.isoCode)
            }
        }
    }
}