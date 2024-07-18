package id.wendei.lockbox.feature.pin

data class PinState(
    val type: PinScreenType = PinScreenType.Verify,
    val pin: String = "",
    val pinToConfirm: String = "",
    val isError: Boolean = false,
    val errorMessage: String = "",
    val showRegisterPopUp: Boolean = false,
) {
    val listPin = List(6) { if (it < pin.length) pin[it].toString() else "" }
}