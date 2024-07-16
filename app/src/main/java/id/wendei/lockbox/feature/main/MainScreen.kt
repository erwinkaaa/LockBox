package id.wendei.lockbox.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import id.wendei.lockbox.core.util.ScreenWrapper
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
                        else -> {}
                    }
                }
            }
        },
        content = { viewModel, state ->
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        MainTop()
        MainContent()
    }
}

@Preview
@Composable
private fun MainPreview() {
    MainView(
        state = MainState(),
        onIntent = {

        }
    )
}