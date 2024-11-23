package com.albersa.nooroproject.ui.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.albersa.nooroproject.data.models.Condition
import com.albersa.nooroproject.data.models.Current
import com.albersa.nooroproject.data.models.Location
import com.albersa.nooroproject.data.models.Weather
import com.albersa.nooroproject.domain.model.CityWeather
import com.albersa.nooroproject.ui.theme.NooroProjectTheme

@Composable
fun TemperatureCard(
    location: String,
    temperature: String,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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

        Text(
            text = location,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "$temperature\u00B0",
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
fun DetailsCard(
    humidity: String,
    uvIndex: String,
    feelsLike: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        Row(modifier = Modifier
            .padding(20.dp),
        ) {
            DetailItem(
                label = stringResource(R.string.humidity_text),
                value = "$humidity%",
                modifier = Modifier.weight(1f)
            )
            DetailItem(
                label = stringResource(R.string.uv_text),
                value = uvIndex,
                modifier = Modifier.weight(1f)
            )
            DetailItem(
                label = stringResource(R.string.feels_like_text),
                value = "$feelsLike\u00B0",
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Composable
fun DetailItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label)
        Text(text = value)
    }
}

@Composable
fun LocationWeather(
    weather: CityWeather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        TemperatureCard(weather.name, weather.tempF.toString(), weather.iconUrl)
        DetailsCard(weather.humidity.toString(), weather.uvIndex.toString(), weather.feelsLikeF.toString())
    }
}



@Preview(showBackground = true)
@Composable
fun TemperatureCardPreview(modifier: Modifier = Modifier) {
    NooroProjectTheme {
        TemperatureCard("Mexico", "25","R.drawable.cloudy")
    }
}