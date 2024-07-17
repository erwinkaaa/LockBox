package id.wendei.lockbox.feature.form

import id.wendei.lockbox.domain.feature.password.model.Password

sealed class FormIntent {
    data class InitScreen(
        val type: FormScreenType,
        val password: Password
    ) : FormIntent()

    data class UpdateTitle(val data: String) : FormIntent()
    data class UpdateCredential(val data: String) : FormIntent()
    data class UpdatePassword(val data: String) : FormIntent()

    data object Submit : FormIntent()
}