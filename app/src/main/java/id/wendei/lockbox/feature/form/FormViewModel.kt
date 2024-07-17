package id.wendei.lockbox.feature.form

import androidx.lifecycle.viewModelScope
import id.wendei.lockbox.core.util.BaseViewModel
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.usecase.InsertPasswordUseCase
import id.wendei.lockbox.domain.feature.password.usecase.UpdatePasswordUseCase
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
                    password = intent.password
                )
            }

            is FormIntent.UpdateTitle -> setState {
                it.copy(password = it.password.copy(title = intent.data))
            }

            is FormIntent.UpdateCredential -> setState {
                it.copy(password = it.password.copy(credential = intent.data))
            }

            is FormIntent.UpdatePassword -> setState {
                it.copy(password = it.password.copy(password = intent.data))
            }

            is FormIntent.Back -> sendEvent(FormEvent.Back)

            is FormIntent.Submit -> {
                if (currentState.password.isAllFilled.not()) {
                    return
                }
                viewModelScope.launch {
                    when (currentState.type) {
                        FormScreenType.Add -> {
                            insertPasswordUseCase.invoke(password = currentState.password)
                            sendEvent(FormEvent.Back)
                        }

                        FormScreenType.Edit -> {
                            updatePasswordUseCase.invoke(password = currentState.password)
                            sendEvent(FormEvent.Back)
                        }

                        FormScreenType.Undefined -> {

                        }
                    }
                }
            }
        }
    }

}