package id.wendei.lockbox.feature.main

import androidx.lifecycle.viewModelScope
import id.wendei.lockbox.core.util.BaseViewModel
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.usecase.GetAllPasswordUseCase
import id.wendei.lockbox.domain.utility.DomainResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.time.Duration.Companion.seconds

class MainViewModel: BaseViewModel<MainState, MainEvent, MainIntent>(MainState()) {

    private val getAllPasswordUseCase: GetAllPasswordUseCase by inject()

    override fun onIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.LoadData -> loadData()

            is MainIntent.NavigateToDetail ->
                sendEvent(MainEvent.GoToDetail(password = intent.password))

            is MainIntent.Copy -> viewModelScope.launch {
                setState { it.copy(showSnackBar = true) }
                delay(2.seconds)
                setState { it.copy(showSnackBar = false) }
            }
        }
    }

    private fun loadData() = viewModelScope.launch {
        val listPassword = getAllPassword()
        setState {
            it.copy(listPassword = listPassword)
        }
    }

    private suspend fun getAllPassword(): List<Password> {
        return when (val result = getAllPasswordUseCase.invoke()) {
            is DomainResult.Success -> result.data
            is DomainResult.Error -> emptyList()
        }
    }
}