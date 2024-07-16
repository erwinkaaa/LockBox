package id.wendei.lockbox.feature.pin

sealed class PinEvent {
    data class NavigateToConfirmPin(val pin: String): PinEvent()
    data object Back: PinEvent()
    data object NavigateToMain: PinEvent()
}