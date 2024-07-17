package id.wendei.lockbox.feature.detail.part

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.wendei.lockbox.core.composable.noRippleClickable
import id.wendei.lockbox.feature.detail.DetailIntent

@Composable
fun DetailTop(
    onIntent: (DetailIntent) -> Unit
) {
    Card(
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .noRippleClickable(
                        onClick = {
                            onIntent.invoke(DetailIntent.Back)
                        }
                    ),
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .height(60.dp)
            )
            Text(
                text = "Detail",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .noRippleClickable(
                        onClick = {
                            onIntent.invoke(DetailIntent.NavigateToEdit)
                        }
                    ),
                imageVector = Icons.Rounded.Create,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(32.dp))
            Image(
                modifier = Modifier
                    .noRippleClickable(
                        onClick = {
                            onIntent.invoke(DetailIntent.ShowDeletePopUp(show = true))
                        }
                    ),
                imageVector = Icons.Rounded.Delete,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Preview
@Composable
private fun DetailTopPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        DetailTop(
            onIntent = {

            }
        )
    }
}