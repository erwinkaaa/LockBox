package id.wendei.lockbox.feature.detail

import id.wendei.lockbox.domain.feature.password.model.Password

sealed class DetailEvent {
    data object Back : DetailEvent()
    data class NavigateToEdit(val password: Password) : DetailEvent()
}