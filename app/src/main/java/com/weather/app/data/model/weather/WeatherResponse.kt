package com.weather.app.data.model.weather

import com.squareup.moshi.Json

data class WeatherModel(
    @field:Json(name = "location")
    val current: Current,
    @field:Json(name = "current")
    val location: Location
)

data class Current(
    val last_updated_epoch: Long,
    val last_updated: String,
    val temp_c: Int,
    val temp_f: Double,
    val is_day: Int,
    val condition: Condition,
    val wind_mph: Int,
    val wind_kph: Int,
    val wind_degree: Int
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int,
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Long,
    val localtime: String
)