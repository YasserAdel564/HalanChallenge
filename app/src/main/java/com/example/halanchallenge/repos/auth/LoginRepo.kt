package com.example.halanchallenge.repos.auth


import com.example.halanchallenge.data.model.login.LoginBody
import com.example.halanchallenge.data.model.login.LoginResponse
import com.example.halanchallenge.data.storage.remote.RetrofitApiService
import com.example.halanchallenge.utils.DataResource
import com.example.halanchallenge.utils.safeApiCall
import javax.inject.Inject

class LoginRepo
@Inject
constructor(
    private val retrofitApiService: RetrofitApiService,
) {
    suspend fun login(loginBody: LoginBody): DataResource<LoginResponse> {
        return safeApiCall { retrofitApiService.loginAsync(body = loginBody) }
    }
}