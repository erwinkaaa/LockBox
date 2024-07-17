package id.wendei.lockbox.domain.feature.password.model

import androidx.compose.ui.graphics.Color
import id.wendei.lockbox.feature.form.part.PasswordStrength

data class Password(
    val id: Long = 0,
    val title: String = "",
    val credential: String = "",
    val password: String = ""
) {
    val isAllFilled = title.isNotBlank() and credential.isNotBlank() and password.isNotBlank()

    val passwordMasked = "*".repeat(password.length)

    val passwordStrength: PasswordStrength = checkPasswordStrength(password = password)

    private fun checkPasswordStrength(password: String): PasswordStrength {
        val lengthCriteria = password.length >= 8
        val uppercaseCriteria = password.any { it.isUpperCase() }
        val lowercaseCriteria = password.any { it.isLowerCase() }
        val digitCriteria = password.any { it.isDigit() }
        val specialCharCriteria = password.any { "!@#$%^&*()_-+=<>?/{}~|".contains(it) }

        // Count how many criteria are met
        val criteriaMet = listOf(
            lengthCriteria,
            uppercaseCriteria,
            lowercaseCriteria,
            digitCriteria,
            specialCharCriteria
        ).count { it }

        return when (criteriaMet) {
            5 -> PasswordStrength(criteriaMet, "Very Strong", Color.Green)
            4 -> PasswordStrength(criteriaMet, "Strong", Color.Green)
            3 -> PasswordStrength(criteriaMet, "Medium", Color(0xFFFF4500))
            2 -> PasswordStrength(criteriaMet, "Weak", Color.Red)
            1 -> PasswordStrength(criteriaMet, "Very Weak", Color.Red)
            else -> PasswordStrength(0, "", Color.LightGray)
        }
    }
}
