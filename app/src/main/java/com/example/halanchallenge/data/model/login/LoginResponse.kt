package com.example.halanchallenge.data.model.login

import com.squareup.moshi.Json

data class LoginResponse (
    @field:Json(name = "message")
    var message: String? = null,
    @field:Json(name = "status")
    var status: String? = null,
    @field:Json(name = "token")
    var token: String? = null,
    @field:Json(name = "profile")
    var userInfo: UserInfo? = null,
)