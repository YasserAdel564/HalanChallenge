package com.example.halanchallenge.repos.weather


import com.example.halanchallenge.data.model.weather.WeatherBody
import com.example.halanchallenge.data.model.weather.WeatherModel
import com.example.halanchallenge.data.storage.remote.RetrofitApiService
import com.example.halanchallenge.utils.DataResource
import com.example.halanchallenge.utils.safeApiCall
import javax.inject.Inject

class WeatherRepo
@Inject
constructor(
    private val retrofitApiService: RetrofitApiService,
) {
    suspend fun getWeather(body: WeatherBody): DataResource<WeatherModel> {
        return safeApiCall {
            retrofitApiService.getWeatherData(
                apiToken = body.apiToken,
                city = body.city,
                days = body.days,
                aqi = body.aqi,
                alerts = body.alerts
            )
        }
    }
}