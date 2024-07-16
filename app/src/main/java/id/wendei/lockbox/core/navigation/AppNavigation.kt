package id.wendei.lockbox.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.wendei.lockbox.feature.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = LocalNavController.current
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Splash,
    ) {
        composable<AppRoutes.Splash> {
            SplashScreen()
        }
        composable<AppRoutes.Pin> {

        }
        composable<AppRoutes.Main> {

        }
    }
}