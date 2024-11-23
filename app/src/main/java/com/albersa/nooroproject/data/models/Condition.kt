package com.albersa.nooroproject.data.models

import com.squareup.moshi.Json

data class Condition(
    @Json(name = "icon")
    val iconUrl: String?
)
