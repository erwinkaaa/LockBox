package id.wendei.lockbox.core.navigation

import android.os.Parcelable
import id.wendei.lockbox.feature.pin.PinScreenType
import kotlinx.parcelize.Parcelize

sealed class AppDestination : Parcelable {

    @Parcelize
    data object Splash : AppDestination()

    @Parcelize
    data class Pin(
        val pin: String = "",
        val type: PinScreenType = PinScreenType.Verify
    ) : AppDestination()

    @Parcelize
    data object Main : AppDestination()

}