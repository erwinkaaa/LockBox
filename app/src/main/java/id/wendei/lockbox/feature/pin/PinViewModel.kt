package id.wendei.lockbox.feature.pin

import id.wendei.lockbox.core.util.BaseViewModel

class PinViewModel : BaseViewModel<PinState, PinEvent, PinIntent>(PinState()) {

    override fun onIntent(intent: PinIntent) {
        when (intent) {
            is PinIntent.Init -> setState {
                it.copy(
                    type = intent.type,
                    pinToConfirm = intent.pin
                )
            }

            is PinIntent.Back -> sendEvent(PinEvent.Back)

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
                    PinScreenType.Register -> {
                        sendEvent(PinEvent.NavigateToConfirmPin(pin = currentState.pin))
                    }

                    PinScreenType.RegisterConfirmation -> {
                        if (currentState.pin == currentState.pinToConfirm) {
                            sendEvent(PinEvent.NavigateToMain)
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
                        val pin = "123456"
                        if (currentState.pin == pin) {
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