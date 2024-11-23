package com.albersa.nooroproject.domain

import com.albersa.nooroproject.data.weather.CityWeatherRepository
import com.albersa.nooroproject.domain.extensions.toDomain
import com.albersa.nooroproject.domain.model.CityWeather
import javax.inject.Inject

class CityWeatherUseCase @Inject constructor(private val cityWeatherRepository: CityWeatherRepository) {
    suspend fun getWeatherByCity(city: String): CityWeather {
        return cityWeatherRepository.getWeatherByCity(city).toDomain()
    }
}