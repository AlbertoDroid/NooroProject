package com.albersa.nooroproject.data.network.services

import com.albersa.nooroproject.data.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/current.json")
    suspend fun getCurrent(@Query(value = "q") city: String): Weather
}