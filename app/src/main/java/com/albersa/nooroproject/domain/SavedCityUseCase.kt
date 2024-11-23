package com.albersa.nooroproject.domain

import com.albersa.nooroproject.data.common.Constants
import com.albersa.nooroproject.data.weather.CityWeatherRepository
import javax.inject.Inject

class SavedCityUseCase @Inject constructor(private val repository: CityWeatherRepository) {
    fun getSavedCity(): String? {
        return repository.getSavedCity(Constants.key_saved_city)
    }

    fun saveCity(city: String) {
        repository.saveCity(Constants.key_saved_city, city)
    }
}