package id.wendei.lockbox.core.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import dev.olshevski.navigation.reimagined.AnimatedNavHost
import dev.olshevski.navigation.reimagined.NavBackHandler
import id.wendei.lockbox.BuildConfig
import id.wendei.lockbox.feature.detail.DetailScreen
import id.wendei.lockbox.feature.form.FormScreen
import id.wendei.lockbox.feature.main.MainScreen
import id.wendei.lockbox.feature.pin.PinScreen
import id.wendei.lockbox.feature.splash.SplashScreen

@SuppressLint("RestrictedApi")
@Composable
fun AppNavigation() {
    val navController = LocalNavController.current

    if (BuildConfig.DEBUG) {
        LaunchedEffect(key1 = navController.backstack) {
            println(navController.backstack.entries)
        }
    }

    NavBackHandler(navController)

    AnimatedNavHost(navController) { destination ->
        when (destination) {
            is AppDestination.Splash -> {
                SplashScreen()
            }

            is AppDestination.Pin -> {
                PinScreen(
                    pin = destination.pin,
                    type = destination.type
                )
            }

            is AppDestination.Main -> {
                MainScreen()
            }

            is AppDestination.Detail -> {
                DetailScreen(
                    password = destination.password
                )
            }

            is AppDestination.Form -> {
                FormScreen(
                    type = destination.type,
                    password = destination.password
                )
            }
        }
    }
}