package com.example.halanchallenge.data.storage.remote


import com.example.halanchallenge.data.model.weather.WeatherModel
import retrofit2.http.*

interface RetrofitApiService {

    @GET("/v1/current.json?")
    suspend fun getWeatherData(
        @Query("key") apiToken: String?,
        @Query("q") city: String?,
        @Query("days") days: String?,
        @Query("aqi") aqi: String?,
        @Query("alerts") alerts: String?
    ): WeatherModel

}