package com.piotrbahlaj.globeboard.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.piotrbahlaj.globeboard.features.countryDetail.presentation.CountryDetailScreen
import com.piotrbahlaj.globeboard.features.dashboard.presentation.DashboardScreen
import com.piotrbahlaj.globeboard.features.explorer.presentation.ExplorerListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    topPadding: Dp,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard,
        modifier = Modifier.padding(top = topPadding)
    ) {
        composable<Routes.Dashboard> {
            DashboardScreen(
                onCountryClick = { isoCode ->
                    navController.navigate(Routes.CountryDetail(isoCode))
                }
            )
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