package id.wendei.lockbox.feature.form

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import id.wendei.lockbox.core.util.BaseViewModel
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.usecase.InsertPasswordUseCase
import id.wendei.lockbox.domain.feature.password.usecase.UpdatePasswordUseCase
import id.wendei.lockbox.feature.form.part.PasswordStrength
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class FormViewModel : BaseViewModel<FormState, FormEvent, FormIntent>(FormState()) {

    private val insertPasswordUseCase: InsertPasswordUseCase by inject()
    private val updatePasswordUseCase: UpdatePasswordUseCase by inject()

    override fun onIntent(intent: FormIntent) {
        when (intent) {
            is FormIntent.InitScreen -> setState {
                it.copy(
                    type = intent.type,
                    id = intent.password.id,
                    title = intent.password.title,
                    credential = intent.password.credential,
                    password = intent.password.password,
                    passwordStrength = checkPasswordStrength(intent.password.password)
                )
            }

            is FormIntent.UpdateTitle -> setState {
                it.copy(title = intent.data)
            }

            is FormIntent.UpdateCredential -> setState {
                it.copy(credential = intent.data)
            }

            is FormIntent.UpdatePassword -> setState {
                it.copy(
                    password = intent.data,
                    passwordStrength = checkPasswordStrength(intent.data)
                )
            }

            is FormIntent.Submit -> viewModelScope.launch {
                when (currentState.type) {
                    is FormScreenType.Add -> {
                        insertPasswordUseCase.invoke(
                            password = Password(
                                title = currentState.title,
                                credential = currentState.credential,
                                password = currentState.password
                            )
                        )
                        sendEvent(FormEvent.Back)
                    }

                    is FormScreenType.Edit -> {
                        updatePasswordUseCase.invoke(
                            password = Password(
                                id =  currentState.id,
                                title = currentState.title,
                                credential = currentState.credential,
                                password = currentState.password
                            )
                        )
                        sendEvent(FormEvent.Back)
                    }

                    is FormScreenType.Undefined -> {

                    }
                }
            }
        }
    }

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