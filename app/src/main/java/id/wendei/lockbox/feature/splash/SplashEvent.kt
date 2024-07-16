package id.wendei.lockbox.feature.splash

import id.wendei.lockbox.feature.pin.PinScreenType

sealed class SplashEvent {
    data class NavigateToPin(val type: PinScreenType): SplashEvent()
}