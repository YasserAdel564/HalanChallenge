package com.example.halanchallenge.utils

object Constants {
    const val BASE_URL = "https://assessment-sn12.halan.io"
    const val symbols = "0123456789/?!:;%"

    enum class ErrorType {
        NETWORK,
        TIMEOUT,
        UNKNOWN
    }
}