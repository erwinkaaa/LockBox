package id.wendei.lockbox.feature.splash

import androidx.lifecycle.viewModelScope
import id.wendei.lockbox.core.util.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SplashViewModel: BaseViewModel<SplashState, SplashEvent, SplashIntent>(SplashState()) {

    override fun onIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.Init -> viewModelScope.launch {
                delay(1.seconds)
                setState { it.copy(isLogoShow = true) }
                delay(1.seconds)
                setState { it.copy(isTitleShow = true) }
                delay(1.seconds)
                sendEvent(SplashEvent.NavigateToPin)
            }
        }
    }

}