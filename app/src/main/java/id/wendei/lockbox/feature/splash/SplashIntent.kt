package id.wendei.lockbox.feature.splash

sealed class SplashIntent {
    data object Init: SplashIntent()
}