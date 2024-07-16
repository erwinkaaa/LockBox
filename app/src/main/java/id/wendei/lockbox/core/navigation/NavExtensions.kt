package id.wendei.lockbox.core.navigation

import androidx.navigation.NavHostController

fun NavHostController.replace(routes: AppRoutes) {
    navigate(routes) {
        popUpTo(AppRoutes.Splash) {
            inclusive = true
        }
    }
}