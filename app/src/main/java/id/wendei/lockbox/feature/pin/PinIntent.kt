package id.wendei.lockbox.feature.pin

sealed class PinIntent {
    data class Init(val pin: String, val type: PinScreenType) : PinIntent()
    data object Back : PinIntent()
    data class ShowRegisterPopUp(val show: Boolean) : PinIntent()
    data class UpdatePin(val pin: String) : PinIntent()
    data object DeletePin : PinIntent()
    data object Submit : PinIntent()
}