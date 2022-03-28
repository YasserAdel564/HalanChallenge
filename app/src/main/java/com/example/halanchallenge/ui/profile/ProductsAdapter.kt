package com.example.halanchallenge.ui.profile

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.halanchallenge.R
import com.example.halanchallenge.data.model.product.ProductInfo


class ProductsAdapter : BaseQuickAdapter<ProductInfo, BaseViewHolder>(R.layout.product_item) {

    override fun convert(helper: BaseViewHolder, item: ProductInfo) {
        helper.addOnClickListener(R.id.more_btn)
        helper.setText(R.id.product_item_title_tv, item.name_ar)
        val productImage =helper.getView<ImageView>(R.id.product_iv)
        Glide.with(mContext).load(item.image).into(productImage)


    }


}

