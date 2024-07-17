package id.wendei.lockbox.feature.main.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.wendei.lockbox.R
import id.wendei.lockbox.feature.main.MainIntent
import id.wendei.lockbox.feature.main.MainState

@Composable
fun MainContent(
    state: MainState,
    onIntent: (MainIntent) -> Unit
) {
    val localClipboardManager = LocalClipboardManager.current

    if (state.listPassword.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
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
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.empty_image),
                contentDescription = null
            )
            Text(
                text = "You have no password yet.",
                fontSize = 18.sp
            )
            Text(
                text = "Start saving your password!",
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        MainTop(
            onIntent = {

            }
        )
        MainContent(
            state = MainState(
                listPassword = listOf(
//                    Password(
//                        id = 1,
//                        title = "Test A",
//                        credential = "abcd@gmail.com",
//                        password = "123456"
//                    ),
//                    Password(
//                        id = 2,
//                        title = "Test B",
//                        credential = "abcd@gmail.com",
//                        password = "123456"
//                    ),
//                    Password(
//                        id = 3,
//                        title = "Test C",
//                        credential = "abcd@gmail.com",
//                        password = "123456"
//                    ),
                )
            ),
            onIntent = {

            }
        )
    }
}