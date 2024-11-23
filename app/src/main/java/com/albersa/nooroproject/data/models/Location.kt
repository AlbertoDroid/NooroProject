package com.albersa.nooroproject.data.models

import com.squareup.moshi.Json

data class Location(
    @Json(name = "region")
    val region: String,

    @Json(name = "name")
    val name: String
)
