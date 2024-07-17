package id.wendei.lockbox.feature.form

import id.wendei.lockbox.domain.feature.password.model.Password

data class FormState(
    val type: FormScreenType = FormScreenType.Undefined,
    val password: Password = Password()
)