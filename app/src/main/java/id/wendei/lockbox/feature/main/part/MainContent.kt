package id.wendei.lockbox.feature.main.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.feature.main.MainIntent
import id.wendei.lockbox.feature.main.MainState

@Composable
fun MainContent(
    state: MainState,
    onIntent: (MainIntent) -> Unit
) {
    val localClipboardManager = LocalClipboardManager.current

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = state.listPassword,
            key = { it.id }
        ) { password ->
            MainItem(
                password = password,
                onClick = {
                    onIntent.invoke(MainIntent.NavigateToDetail(password = password))
                },
                onCopy = {
                    localClipboardManager.setText(AnnotatedString(password.password))
                    onIntent.invoke(MainIntent.Copy)
                }
            )
        }
    }
}

@Preview
@Composable
private fun MainContentPreview() {
    MainContent(
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