package id.wendei.lockbox.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoutes {

    @Serializable
    data object Splash : AppRoutes()

    @Serializable
    data object Pin : AppRoutes()

    @Serializable
    data object Main : AppRoutes()

    @Serializable
    data class SecondScreen(val customPrimitive: String) : AppRoutes()

}