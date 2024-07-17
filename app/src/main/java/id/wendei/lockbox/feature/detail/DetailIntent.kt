package id.wendei.lockbox.feature.detail

import id.wendei.lockbox.domain.feature.password.model.Password

sealed class DetailIntent {
    data class InitScreen(val password: Password) : DetailIntent()
    data object Back : DetailIntent()
    data object NavigateToEdit : DetailIntent()
    data object SetPasswordVisibility : DetailIntent()
    data class ShowDeletePopUp(val show: Boolean) : DetailIntent()
    data object Delete : DetailIntent()
    data object Copy : DetailIntent()
}