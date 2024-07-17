package id.wendei.lockbox.feature.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.olshevski.navigation.reimagined.pop
import id.wendei.lockbox.core.util.ScreenWrapper
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.feature.form.part.FormContent
import id.wendei.lockbox.feature.form.part.FormTop
import id.wendei.lockbox.feature.form.part.PasswordStrength
import kotlinx.coroutines.launch

enum class FormScreenType {
    Add,
    Edit,
    Undefined
}

@Composable
fun FormScreen(
    type: FormScreenType,
    password: Password
) {
    ScreenWrapper<FormViewModel, FormState, FormEvent, FormIntent>(
        events = { events, scope, navController ->
            scope.launch {
                events.collect { event ->
                    when (event) {
                        is FormEvent.Back ->
                            navController.pop()
                    }
                }
            }
        },
        content = { viewModel, state ->
            LaunchedEffect(key1 = Unit) {
                viewModel.onIntent(FormIntent.InitScreen(type = type, password = password))
            }
            FormView(
                state = state,
                onIntent = viewModel::onIntent
            )
        }
    )
}

@Composable
private fun FormView(
    state: FormState,
    onIntent: (FormIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        FormTop(
            state = state,
            onIntent = onIntent
        )
        FormContent(
            state = state,
            onIntent = onIntent
        )
    }
}

@Preview
@Composable
private fun MainPreview() {
    FormView(
        state = FormState(
            passwordStrength = PasswordStrength(
                criteriaMet = 1,
                description = "Weak",
                color = Color.Red
            )
        ),
        onIntent = {

        }
    )
}