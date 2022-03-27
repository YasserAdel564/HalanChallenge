package com.example.halanchallenge.data.model.product

import com.squareup.moshi.Json

data class ProductInfo (
    @field:Json(name = "id")
    var id: Int? = null,
    @field:Json(name = "name_ar")
    var name_ar: String? = null,
    @field:Json(name = "deal_description")
    var deal_description: String? = null,
    @field:Json(name = "brand")
    var brand: String? = null,
    @field:Json(name = "image")
    var image: String? = null,
    @field:Json(name = "name_en")
    var name_en: String? = null,
    @field:Json(name = "price")
    var price: Int? = null,
    @field:Json(name = "images")
    var images: List<String>? = null,
)