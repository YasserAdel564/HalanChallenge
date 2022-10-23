package com.example.halanchallenge.utils

object Constants {
    const val BASE_URL = "https://api.weatherapi.com"
    const val API_KEY = "535372d838d64fff860210548222210"

    enum class ErrorType {
        NETWORK,
        TIMEOUT,
        UNKNOWN
    }
}