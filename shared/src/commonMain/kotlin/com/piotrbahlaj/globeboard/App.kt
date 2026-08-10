package com.piotrbahlaj.globeboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.piotrbahlaj.globeboard.core.navigation.Routes
import com.piotrbahlaj.globeboard.features.countryDetail.presentation.CountryDetailScreen
import com.piotrbahlaj.globeboard.features.explorer.presentation.ExplorerListScreen

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        Scaffold { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Routes.ExplorerList,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Routes.Dashboard> {
                    // DashboardScreen
                }
                composable<Routes.ExplorerList> {
                    ExplorerListScreen(
                        onCountryClick = { isoCode ->
                            navController.navigate(Routes.CountryDetail(isoCode))
                        }
                    )
                }
                composable<Routes.CountryDetail> { backStackEntry ->
                    val args: Routes.CountryDetail = backStackEntry.toRoute()
                    CountryDetailScreen(isoCode = args.isoCode)
                }
            }
        }
    }
}