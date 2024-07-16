package id.wendei.lockbox.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Suppress("CAST_NEVER_SUCCEEDS")
val LocalNavController = compositionLocalOf {
    NavController as NavHostController
}

@Composable
fun NavigationWrapper(content: @Composable () -> Unit) {
    CompositionLocalProvider(value = LocalNavController provides rememberNavController()) {
        content()
    }
}