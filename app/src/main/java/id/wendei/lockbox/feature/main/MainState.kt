package id.wendei.lockbox.feature.main

import id.wendei.lockbox.domain.feature.password.model.Password

data class MainState(
    val listPassword: List<Password> = listOf(),
    val showSnackBar: Boolean = false
)