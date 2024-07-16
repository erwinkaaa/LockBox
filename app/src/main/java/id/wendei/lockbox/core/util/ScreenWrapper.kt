package id.wendei.lockbox.core.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import id.wendei.lockbox.core.navigation.LocalNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel

@Composable
inline fun <reified ViewModel : BaseViewModel<State, Event, Intent>, State, Event, Intent> ScreenWrapper(
    viewModel: ViewModel = koinViewModel(),
    crossinline events: (Flow<Event>, CoroutineScope, NavHostController) -> Unit,
    crossinline content: @Composable (viewModel: ViewModel, state: State) -> Unit,
) {
    val navController = LocalNavController.current

    val state: State? by viewModel.state.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    events.invoke(viewModel.event, viewModel.viewModelScope, navController)

    state?.let {
        content(viewModel, it)
    }

    if (loadingState) {
        AlertDialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            title = {
                Text("Memuat")
            },
            text = {
                Text("Mohon tunggu..")
            },
            confirmButton = {

            }
        )
    }

    if (errorState.isNotBlank()) {
        AlertDialog(
            onDismissRequest = {
                viewModel.setErrorMessage("")
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            title = {
                Text("Terjadi kesalahan")
            },
            text = {
                Text(errorState)
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.setErrorMessage("")
                    }
                ) {
                    Text("Ok")
                }
            }
        )
    }
}