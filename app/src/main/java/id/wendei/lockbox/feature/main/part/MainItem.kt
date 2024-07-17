package id.wendei.lockbox.feature.main.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.wendei.lockbox.R
import id.wendei.lockbox.core.composable.noRippleClickable
import id.wendei.lockbox.domain.feature.password.model.Password

@Composable
fun MainItem(
    password: Password,
    onClick: () -> Unit,
    onCopy: () -> Unit
) {
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    text = password.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = password.credential,
                    maxLines = 1
                )
            }
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .noRippleClickable(onClick = onCopy),
                painter = painterResource(id = R.drawable.copy),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun MainItemPreview() {
    MainItem(
        password = Password(
            id = 1,
            title = "Test A",
            credential = "abcd@gmail.com",
            password = "123456"
        ),
        onClick = {

        },
        onCopy = {

        }
    )
}