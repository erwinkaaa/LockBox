package id.wendei.lockbox.feature.splash

import androidx.lifecycle.viewModelScope
import id.wendei.lockbox.core.util.BaseViewModel
import id.wendei.lockbox.domain.feature.auth.usecase.GetPinUseCase
import id.wendei.lockbox.domain.utility.DomainResult
import id.wendei.lockbox.feature.pin.PinScreenType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.time.Duration.Companion.seconds

class SplashViewModel : BaseViewModel<SplashState, SplashEvent, SplashIntent>(SplashState()) {

    private val getPinUseCase: GetPinUseCase by inject()

    override fun onIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.Init -> viewModelScope.launch {
                delay(1.seconds)
                setState { it.copy(isLogoShow = true) }
                delay(1.seconds)
                setState { it.copy(isTitleShow = true) }
                delay(1.seconds)

                when (val result = getPinUseCase.invoke()) {
                    is DomainResult.Success -> {
                        if (result.data.isNotBlank()) {
                            sendEvent(SplashEvent.NavigateToPin(type = PinScreenType.Verify))
                        } else {
                            sendEvent(SplashEvent.NavigateToPin(type = PinScreenType.Register))
                        }
                    }
                    is DomainResult.Error -> {

                    }
                }
            }
        }
    }

}