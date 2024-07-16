package id.wendei.lockbox.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import dev.olshevski.navigation.reimagined.NavController
import dev.olshevski.navigation.reimagined.rememberNavController

@Suppress("CAST_NEVER_SUCCEEDS")
val LocalNavController = compositionLocalOf {
    NavController as NavController<AppDestination>
}

@Composable
fun NavigationWrapper(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        value = LocalNavController provides rememberNavController(
            startDestination = AppDestination.Splash
        )
    ) {
        content()
    }
}