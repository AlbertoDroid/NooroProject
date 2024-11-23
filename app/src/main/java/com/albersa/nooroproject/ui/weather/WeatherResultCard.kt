package com.albersa.nooroproject.ui.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import coil3.request.transformations
import coil3.transform.CircleCropTransformation
import com.albersa.nooroproject.R
import com.albersa.nooroproject.ui.theme.NooroProjectTheme

@Composable
fun WeatherResultCard(
    location: String,
    temperature: String,
    imageUrl: String,
    onCardSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 5.dp),
        onClick = { onCardSelected() }
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = location,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "$temperature\u00B0",
                    style = MaterialTheme.typography.displayLarge
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.cloudy)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WeatherResultCardPreview() {
    NooroProjectTheme {
        WeatherResultCard("Mexico", "20", "", onCardSelected = {})
    }
}