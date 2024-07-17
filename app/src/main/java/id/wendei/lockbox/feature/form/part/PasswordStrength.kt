package id.wendei.lockbox.feature.form.part

import androidx.compose.ui.graphics.Color

data class PasswordStrength(
    val criteriaMet: Int = 0,
    val description: String = "",
    val color: Color = Color.Gray
)
