package id.wendei.lockbox.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.olshevski.navigation.reimagined.navigate
import id.wendei.lockbox.core.navigation.AppDestination
import id.wendei.lockbox.core.util.ScreenWrapper
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.feature.main.part.MainContent
import id.wendei.lockbox.feature.main.part.MainTop
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    ScreenWrapper<MainViewModel, MainState, MainEvent, MainIntent>(
        events = { events, scope, navController ->
            scope.launch {
                events.collect { event ->
                    when (event) {
                        is MainEvent.GoToDetail ->
                            navController.navigate(AppDestination.Main)
                    }
                }
            }
        },
        content = { viewModel, state ->
            LaunchedEffect(key1 = Unit) {
                viewModel.onIntent(MainIntent.LoadData)
            }
            MainView(
                state = state,
                onIntent = viewModel::onIntent
            )
        }
    )
}

@Composable
fun MainView(
    state: MainState,
    onIntent: (MainIntent) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = state.showSnackBar) {
        if (state.showSnackBar) {
            snackBarHostState.showSnackbar(
                message = "Password has been copied!",
                duration = SnackbarDuration.Short
            )
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            MainTop()
            MainContent(
                state = state,
                onIntent = onIntent
            )
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    MainView(
        state = MainState(
            listPassword = listOf(
                Password(
                    id = 1,
                    title = "Test A",
                    credential = "abcd@gmail.com",
                    password = "123456"
                ),
                Password(
                    id = 2,
                    title = "Test B",
                    credential = "abcd@gmail.com",
                    password = "123456"
                ),
                Password(
                    id = 3,
                    title = "Test C",
                    credential = "abcd@gmail.com",
                    password = "123456"
                ),
            )
        ),
        onIntent = {

        }
    )
}