package com.albersa.nooroproject.data.weather

import com.albersa.nooroproject.data.models.Weather
import com.albersa.nooroproject.data.network.services.WeatherApiService
import javax.inject.Inject

class CityWeatherRemoteDataSource @Inject constructor(private val serviceApi: WeatherApiService) {
    suspend fun getWeatherByCity(city: String): Weather {
        return serviceApi.getCurrent(city)
    }
}