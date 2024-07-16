package id.wendei.lockbox.feature.splash

sealed class SplashEvent {
    data object NavigateToPin: SplashEvent()
}