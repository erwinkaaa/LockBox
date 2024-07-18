package id.wendei.lockbox.feature.pin

import id.wendei.lockbox.core.util.BaseViewModel
import id.wendei.lockbox.domain.feature.auth.usecase.GetPinUseCase
import id.wendei.lockbox.domain.feature.auth.usecase.SetPinUseCase
import id.wendei.lockbox.domain.utility.DomainResult
import org.koin.core.component.inject

class PinViewModel : BaseViewModel<PinState, PinEvent, PinIntent>(PinState()) {

    private val getPinUseCase: GetPinUseCase by inject()
    private val setPinUseCase: SetPinUseCase by inject()

    override fun onIntent(intent: PinIntent) {
        when (intent) {
            is PinIntent.Init -> {
                setState {
                    it.copy(
                        type = intent.type,
                        pinToConfirm = intent.pin
                    )
                }

                if (currentState.type == PinScreenType.Register) {
                    onIntent(PinIntent.ShowRegisterPopUp(show = true))
                }

                if (currentState.type == PinScreenType.Verify) {
                    when (val result = getPinUseCase.invoke()) {
                        is DomainResult.Success -> {
                            setState {
                                it.copy(pinToConfirm = result.data)
                            }
                        }

                        is DomainResult.Error -> {}
                    }
                }
            }

            is PinIntent.Back -> sendEvent(PinEvent.Back)

            is PinIntent.ShowRegisterPopUp -> setState { it.copy(showRegisterPopUp = intent.show) }

            is PinIntent.UpdatePin -> {
                setState {
                    it.copy(
                        pin = if (it.pin.length < 6)
                            it.pin + intent.pin
                        else
                            it.pin,
                        isError = false,
                        errorMessage = ""
                    )
                }
            }

            is PinIntent.DeletePin -> {
                setState {
                    it.copy(
                        pin = it.pin.dropLast(1),
                        isError = false,
                        errorMessage = ""
                    )
                }
            }

            is PinIntent.Submit -> {
                when (currentState.type) {
                    PinScreenType.Register ->
                        sendEvent(PinEvent.NavigateToConfirmPin(pin = currentState.pin))

                    PinScreenType.RegisterConfirmation -> {
                        if (currentState.pin == currentState.pinToConfirm) {
                            when (setPinUseCase.invoke(pin = currentState.pin)) {
                                is DomainResult.Success -> sendEvent(PinEvent.NavigateToMain)
                                is DomainResult.Error -> {}
                            }
                        } else {
                            setState {
                                it.copy(
                                    isError = true,
                                    errorMessage = "PIN is not same!"
                                )
                            }
                        }
                    }

                    PinScreenType.Verify -> {
                        if (currentState.pin == currentState.pinToConfirm) {
                            sendEvent(PinEvent.NavigateToMain)
                        } else {
                            setState {
                                it.copy(
                                    isError = true,
                                    errorMessage = "Wrong PIN!"
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}