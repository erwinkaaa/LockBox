package id.wendei.lockbox.feature.form

import id.wendei.lockbox.feature.form.part.PasswordStrength

data class FormState(
    val type: FormScreenType = FormScreenType.Undefined,
    val id: Long = 0,
    val title: String = "",
    val credential: String = "",
    val password: String = "",
    val passwordStrength: PasswordStrength = PasswordStrength()
)