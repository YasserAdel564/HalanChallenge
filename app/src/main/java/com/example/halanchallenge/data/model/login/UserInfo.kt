package com.example.halanchallenge.data.model.login

import com.squareup.moshi.Json

data class UserInfo (
    @field:Json(name = "username")
    var username: String? = null,
    @field:Json(name = "image")
    var image: String? = null,
    @field:Json(name = "name")
    var name: String? = null,
    @field:Json(name = "email")
    var email: String? = null,
    @field:Json(name = "phone")
    var phone: String? = null,
)