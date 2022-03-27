package com.example.halanchallenge.data.model.product

import com.squareup.moshi.Json

data class ProductResponse (
    @field:Json(name = "status")
    var status: String? = null,
    @field:Json(name = "products")
    var products: List<ProductInfo>? = null,
)