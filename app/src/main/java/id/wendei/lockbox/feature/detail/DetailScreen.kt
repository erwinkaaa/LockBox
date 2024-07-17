package id.wendei.lockbox.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.pop
import id.wendei.lockbox.core.composable.ConfirmDialog
import id.wendei.lockbox.core.navigation.AppDestination
import id.wendei.lockbox.core.util.ScreenWrapper
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.feature.detail.part.DetailContent
import id.wendei.lockbox.feature.detail.part.DetailTop
import id.wendei.lockbox.feature.form.FormScreenType
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    password: Password
) {
    ScreenWrapper<DetailViewModel, DetailState, DetailEvent, DetailIntent>(
        events = { events, scope, navController ->
            scope.launch {
                events.collect { event ->
                    when (event) {
                        is DetailEvent.Back ->
                            navController.pop()

                        is DetailEvent.NavigateToEdit ->
                            navController.navigate(
                                AppDestination.Form(
                                    type = FormScreenType.Edit,
                                    password = event.password
                                )
                            )
                    }
                }
            }
        },
        content = { viewModel, state ->
            LaunchedEffect(key1 = Unit) {
                viewModel.onIntent(DetailIntent.InitScreen(password = password))
            }
            DetailView(
                state = state,
                onIntent = viewModel::onIntent
            )
        }
    )
}

@Composable
private fun DetailView(
    state: DetailState,
    onIntent: (DetailIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        DetailTop(
            onIntent = onIntent
        )
        DetailContent(
            state = state,
            onIntent = onIntent
        )
    }

    if (state.showDeletePopUp) {
        ConfirmDialog(
            title = "Delete Confirmation.",
            message = "Are you sure to delete this password?",
            onConfirm = {
                onIntent.invoke(DetailIntent.ShowDeletePopUp(show = false))
                onIntent.invoke(DetailIntent.Delete)
            },
            onClose = {
                onIntent.invoke(DetailIntent.ShowDeletePopUp(show = false))
            }
        )
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    DetailView(
        state = DetailState(
            password = Password(
                title = "Gmail",
                credential = "abcd@gmail.com",
                password = "123456"
            ),
//            showDeletePopUp = true
        ),
        onIntent = {

        }
    )
}