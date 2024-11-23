package com.albersa.nooroproject.ui.weather

import com.albersa.nooroproject.ui.models.UserMessage

data class CityWeatherUiState(
    val isLoading: Boolean,
    val message: UserMessage?,
    val showSearchResults: Boolean,
    val showWeather: Boolean
)