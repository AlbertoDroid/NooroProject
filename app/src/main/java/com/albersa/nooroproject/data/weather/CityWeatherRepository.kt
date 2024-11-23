package com.albersa.nooroproject.data.weather

import com.albersa.nooroproject.data.common.datasources.SharedPreferencesDataSource
import com.albersa.nooroproject.data.models.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityWeatherRepository @Inject constructor(private val sharedPreferencesDataSource: SharedPreferencesDataSource, private val cityWeatherDataSource: CityWeatherRemoteDataSource) {

    fun getSavedCity(key: String): String? {
        return sharedPreferencesDataSource.getString(key)
    }

    fun saveCity(key:String, city: String) {
        sharedPreferencesDataSource.saveString(key, city)
    }

    suspend fun getWeatherByCity(city: String): Weather {
        return withContext(Dispatchers.IO) {
            cityWeatherDataSource.getWeatherByCity(city)
        }
    }
}