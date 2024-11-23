package com.albersa.nooroproject.domain.extensions

import com.albersa.nooroproject.data.models.Weather
import com.albersa.nooroproject.domain.model.CityWeather

fun Weather.toDomain(): CityWeather {
    return CityWeather(
        tempF = this.current.tempF,
        humidity = this.current.humidity,
        uvIndex = this.current.uvIndex,
        feelsLikeF = this.current.feelsLikeF,
        name = this.location.name,
        iconUrlRaw = this.current.condition.iconUrl
    )
}