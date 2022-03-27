package com.example.halanchallenge.repos.products


import com.example.halanchallenge.data.model.login.LoginBody
import com.example.halanchallenge.data.model.login.LoginResponse
import com.example.halanchallenge.data.model.product.ProductResponse
import com.example.halanchallenge.data.storage.local.PreferencesHelper
import com.example.halanchallenge.data.storage.remote.RetrofitApiService
import com.example.halanchallenge.utils.DataResource
import com.example.halanchallenge.utils.safeApiCall
import javax.inject.Inject

class ProductsRepo
@Inject
constructor(
    private val retrofitApiService: RetrofitApiService,
    private val helper: PreferencesHelper,
) {
    suspend fun getProducts(): DataResource<ProductResponse> {
        return safeApiCall { retrofitApiService.productsAsync(token = "Bearer " + helper.token) }
    }
}