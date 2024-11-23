package com.albersa.nooroproject.domain.model

data class CityWeather(
    val tempF: Float,
    val humidity: Int,
    val uvIndex: Float,
    val feelsLikeF: Float,
    val name: String,
    private val iconUrlRaw: String?
) {
    val iconUrl = "https:$iconUrlRaw"
}
