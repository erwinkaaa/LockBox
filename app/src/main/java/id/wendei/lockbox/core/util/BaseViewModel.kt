package id.wendei.lockbox.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<STATE, EVENT, INTENT>(
    initialState: STATE,
) : ViewModel(), KoinComponent {

    abstract fun onIntent(intent: INTENT)

    // UI state
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    val currentState: STATE get() = state.value
    protected fun setState(update: (old: STATE) -> STATE): STATE = _state.updateAndGet(update)

    // UI event
    private val eventChannel = Channel<EVENT>()
    val event = eventChannel.receiveAsFlow()

    fun sendEvent(event: EVENT) = viewModelScope.launch {
        eventChannel.send(event)
    }

    // loading state
    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    fun setLoading(isLoading: Boolean) {
        _loadingState.value = isLoading
    }

    // error state
    private val _errorState = MutableStateFlow("")
    val errorState = _errorState.asStateFlow()

    fun setErrorMessage(errorMessage: String) {
        _errorState.value = errorMessage
    }

}
