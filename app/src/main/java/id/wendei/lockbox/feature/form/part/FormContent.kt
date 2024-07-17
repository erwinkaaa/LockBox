package id.wendei.lockbox.feature.form.part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import id.wendei.lockbox.feature.form.FormIntent
import id.wendei.lockbox.feature.form.FormState

@Composable
fun FormContent(
    state: FormState,
    onIntent: (FormIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.title,
            onValueChange = {
                onIntent.invoke(FormIntent.UpdateTitle(data = it))
            },
            label = {
                Text(text = "Title")
            },
            placeholder = {
                Text(text = "Gmail")
            },
            maxLines = 1
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.credential,
            onValueChange = {
                onIntent.invoke(FormIntent.UpdateCredential(data = it))
            },
            label = {
                Text(text = "Credential (username/email)")
            },
            placeholder = {
                Text(text = "abcd@gmail.com")
            },
            maxLines = 1
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = {
                onIntent.invoke(FormIntent.UpdatePassword(data = it))
            },
            label = {
                Text(text = "Password")
            },
            maxLines = 1
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            PasswordStrengthView(
                description = state.passwordStrength.description,
                criteriaMet = state.passwordStrength.criteriaMet,
                color = state.passwordStrength.color
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Tips :",
                fontSize = 18.sp
            )
            Text(text = "Strong password is more than 8 characters, contains Uppercase, Lowercase, number and symbol.")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FormContentPreview() {
    FormContent(
        state = FormState(),
        onIntent = {

        }
    )
}