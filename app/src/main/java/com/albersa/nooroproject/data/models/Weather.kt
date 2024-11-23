package com.albersa.nooroproject.data.models

import com.squareup.moshi.Json

data class Weather(
    @Json(name = "location")
    val location: Location,

    @Json(name = "current")
    val current: Current
)
