package com.example.halanchallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.halanchallenge.data.model.login.UserInfo
import com.example.halanchallenge.data.model.product.ProductInfo
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class SharedVM @Inject
constructor() : ViewModel() {

    val userLiveData = MutableLiveData<UserInfo>()
    fun setUserLiveData(userInfo: UserInfo) {
        userLiveData.value = userInfo
    }


    val productLiveData = MutableLiveData<ProductInfo>()
    fun setProductLiveData(products: ProductInfo) {
        productLiveData.value = products
    }




}