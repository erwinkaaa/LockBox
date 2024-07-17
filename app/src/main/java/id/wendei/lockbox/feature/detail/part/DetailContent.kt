package id.wendei.lockbox.feature.detail.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.wendei.lockbox.core.composable.noRippleClickable
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.feature.detail.DetailIntent
import id.wendei.lockbox.feature.detail.DetailState
import id.wendei.lockbox.feature.form.part.PasswordStrengthView

@Composable
fun DetailContent(
    state: DetailState,
    onIntent: (DetailIntent) -> Unit
) {
    val localClipboardManager = LocalClipboardManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Text(
            text = "Title",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.password.title)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Credential",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = state.password.credential)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Password",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (state.passwordVisible)
                        state.password.password
                    else
                        state.password.passwordMasked
                )
            }
            Image(
                modifier = Modifier
                    .noRippleClickable(
                        onClick = {
                            onIntent.invoke(DetailIntent.SetPasswordVisibility)
                        }
                    ),
                imageVector = if (!state.passwordVisible)
                    Icons.Rounded.Visibility
                else
                    Icons.Rounded.VisibilityOff,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        PasswordStrengthView(
            description = state.password.passwordStrength.description,
            criteriaMet = state.password.passwordStrength.criteriaMet,
            color = state.password.passwordStrength.color
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                localClipboardManager.setText(AnnotatedString(state.password.password))
                onIntent.invoke(DetailIntent.Copy)
            },
            enabled = state.isCopyEnable
        ) {
            Text(
                text = if (state.isCopyEnable)
                    "Copy password"
                else
                    "Password copied!"
            )
        }
    }
}

@Preview
@Composable
private fun DetailContentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        DetailTop(
            onIntent = {

            }
        )
        DetailContent(
            state = DetailState(
                password = Password(
                    title = "Gmail",
                    credential = "abcd@gmail.com",
                    password = "123456"
                )
            ),
            onIntent = {

            }
        )
    }
}