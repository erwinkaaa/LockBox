package id.wendei.lockbox.feature.detail

import id.wendei.lockbox.domain.feature.password.model.Password

data class DetailState(
    val password: Password = Password(),
    val passwordVisible: Boolean = false,
    val showDeletePopUp: Boolean = false,
    val isCopyEnable: Boolean = true
)