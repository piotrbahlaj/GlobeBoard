package com.piotrbahlaj.globeboard

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.piotrbahlaj.globeboard.core.navigation.AppNavHost
import com.piotrbahlaj.globeboard.core.navigation.BottomNavBar
import com.piotrbahlaj.globeboard.core.navigation.shouldShowBottomBar
import com.piotrbahlaj.globeboard.core.theme.GlobeBoardTheme


@Composable
fun App() {
    var isDarkTheme by remember { mutableStateOf(false) }

    GlobeBoardTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination

        Scaffold(
            bottomBar = {
                if (shouldShowBottomBar(currentDestination)) {
                    BottomNavBar(navController, currentDestination)
                }
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                topPadding = innerPadding.calculateTopPadding(),
                isDarkTheme = isDarkTheme,
                onToggleTheme = { isDarkTheme = !isDarkTheme }
            )
        }
    }
}