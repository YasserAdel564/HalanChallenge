package com.example.halanchallenge.ui.productDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.NetworkUtils
import com.example.halanchallenge.data.model.product.ProductInfo
import com.example.halanchallenge.repos.products.ProductsRepo
import com.example.halanchallenge.utils.DataResource
import com.example.halanchallenge.utils.Event
import com.example.halanchallenge.utils.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsVM @Inject
constructor(
    private val repository: ProductsRepo,
) : ViewModel() {

    private var job: Job? = null
    private var _uiState = MutableLiveData<Event<UiStates>>()
    val uiState: LiveData<Event<UiStates>>
        get() = _uiState

    val products: ArrayList<ProductInfo> = arrayListOf<ProductInfo>()

    fun login() {
        if (NetworkUtils.isConnected()) {
            if (job?.isActive == true)
                return
            job = launchJob()
        } else {
            _uiState.value = Event(UiStates.NoConnection)
        }
    }

    private fun launchJob(): Job {
        _uiState.value = Event(UiStates.Loading)
        return CoroutineScope(Dispatchers.Main).launch {
            when (val response = repository.getProducts()) {
                is DataResource.Success -> {
                    val productsResponse = response.value
                    if (productsResponse.products != null)
                        if (productsResponse.products!!.isNotEmpty()) {
                            products.addAll(productsResponse.products!!)
                            _uiState.value = Event(UiStates.Success)
                        } else
                            _uiState.value = Event(UiStates.Error)
                }
                is DataResource.Error -> {
                    _uiState.value = Event(UiStates.NotFound)
                }
            }
        }
    }
}