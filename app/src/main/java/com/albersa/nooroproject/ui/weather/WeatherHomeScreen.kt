package com.albersa.nooroproject.ui.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.albersa.nooroproject.R
import com.albersa.nooroproject.domain.model.CityWeather

@Composable
fun WeatherHomeScreen(
    weather: CityWeather?,
    uiState: CityWeatherUiState,
    onCardSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (uiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (uiState.message != null) {
        UserMessage(
            title = uiState.message.title,
            description = uiState.message.description,
            modifier = modifier
        )
    } else if (uiState.showWeather) {
        weather?.let { LocationWeather(weather, modifier = modifier) }
    } else if (uiState.showSearchResults) {
        weather?.let {
            WeatherResultCard(
                weather.name,
                weather.tempF.toString(),
                weather.iconUrl,
                onCardSelected = onCardSelected,
                modifier = modifier
            )
        }
    }
}
