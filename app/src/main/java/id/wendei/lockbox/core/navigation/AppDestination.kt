package id.wendei.lockbox.core.navigation

import android.os.Parcelable
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.feature.form.FormScreenType
import id.wendei.lockbox.feature.pin.PinScreenType
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

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

    @Parcelize
    data class Form(
        val type: FormScreenType,
        val password: @RawValue Password
    ) : AppDestination()

}