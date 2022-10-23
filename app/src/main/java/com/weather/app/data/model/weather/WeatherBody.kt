package com.example.halanchallenge.data.model.weather


data class WeatherBody(
    var apiToken: String,
    var city: String,
    var days: String,
    var aqi: String,
    var alerts: String,
)