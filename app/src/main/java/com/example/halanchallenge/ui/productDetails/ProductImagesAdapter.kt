package com.example.halanchallenge.ui.productDetails

import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.halanchallenge.R

class ProductImagesAdapter :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.image_view_item) {

    override fun convert(helper: BaseViewHolder, item: String) {
        Glide.with(mContext).load(item)
            .into(helper.getView(R.id.product_image_IV))

    }
}