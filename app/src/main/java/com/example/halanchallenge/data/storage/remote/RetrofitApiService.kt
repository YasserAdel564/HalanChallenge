package com.example.halanchallenge.data.storage.remote


import com.example.halanchallenge.data.model.login.LoginBody
import com.example.halanchallenge.data.model.login.LoginResponse
import com.example.halanchallenge.data.model.product.ProductResponse
import retrofit2.http.*

interface RetrofitApiService {

    @POST("/auth")
    suspend fun loginAsync(
        @Body body: LoginBody?,
    ): LoginResponse


    @POST("/products")
    suspend fun productsAsync(
        @Header("Authorization") token: String
    ): ProductResponse

}