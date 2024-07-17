package id.wendei.lockbox.feature.detail

import androidx.lifecycle.viewModelScope
import id.wendei.lockbox.core.util.BaseViewModel
import id.wendei.lockbox.domain.feature.password.usecase.DeletePasswordUseCase
import id.wendei.lockbox.domain.feature.password.usecase.GetPasswordUseCase
import id.wendei.lockbox.domain.utility.DomainResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.time.Duration.Companion.seconds

class DetailViewModel : BaseViewModel<DetailState, DetailEvent, DetailIntent>(DetailState()) {

    private val getPasswordUseCase: GetPasswordUseCase by inject()
    private val deletePasswordUseCase: DeletePasswordUseCase by inject()

    override fun onIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.InitScreen -> viewModelScope.launch {
                when (val result = getPasswordUseCase.invoke(intent.password)) {
                    is DomainResult.Success -> {
                        setState {
                            it.copy(
                                password = result.data,
                                passwordVisible = false
                            )
                        }
                    }

                    is DomainResult.Error -> {

                    }
                }
            }

            is DetailIntent.Back -> sendEvent(DetailEvent.Back)

            is DetailIntent.NavigateToEdit ->
                sendEvent(DetailEvent.NavigateToEdit(password = currentState.password))

            is DetailIntent.SetPasswordVisibility -> setState {
                it.copy(passwordVisible = !it.passwordVisible)
            }

            is DetailIntent.ShowDeletePopUp -> setState {
                it.copy(showDeletePopUp = !it.showDeletePopUp)
            }

            is DetailIntent.Delete -> viewModelScope.launch {
                when (deletePasswordUseCase.invoke(currentState.password)) {
                    is DomainResult.Success -> {
                        sendEvent(DetailEvent.Back)
                    }

                    is DomainResult.Error -> {

                    }
                }
            }

            is DetailIntent.Copy -> viewModelScope.launch {
                setState { it.copy(isCopyEnable = false) }
                delay(2.seconds)
                setState { it.copy(isCopyEnable = true) }
            }
        }
    }

}