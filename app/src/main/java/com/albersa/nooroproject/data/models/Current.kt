package com.albersa.nooroproject.data.models

import com.squareup.moshi.Json

data class Current(
    @Json(name = "temp_f")
    val tempF: Float,

    @Json(name = "humidity")
    val humidity: Int,

    @Json(name = "uv")
    val uvIndex: Float,

    @Json(name = "feelslike_f")
    val feelsLikeF: Float,

    @Json(name = "condition")
    val condition: Condition
)
