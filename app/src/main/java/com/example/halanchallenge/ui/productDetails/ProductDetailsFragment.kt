package com.example.halanchallenge.ui.productDetails

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.halanchallenge.R
import com.example.halanchallenge.data.model.product.ProductInfo
import com.example.halanchallenge.databinding.ActivityProductDetailsBinding
import com.example.halanchallenge.ui.SharedVM

class ProductDetailsFragment : Fragment() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val sharedVM: SharedVM by activityViewModels()
    private val imagesAdapter = ProductImagesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedVM.productLiveData.observe(viewLifecycleOwner, { fillViews(it) })
        onViewClick()
    }

    private fun fillViews(productInfo: ProductInfo) {
        binding.productDescriptionTv.text = productInfo.deal_description
        binding.productDescriptionTv.movementMethod = ScrollingMovementMethod();
        binding.productTitleTv.text = productInfo.name_ar
        binding.productPriceTv.text = productInfo.price.toString() +requireActivity().resources.getString(R.string.epg)
        productInfo.images?.let { imagesAdapter.replaceData(it) }
        binding.productImagesBanner.adapter = imagesAdapter;
        binding.arIndicator.attachTo(binding.productImagesBanner, true);
    }

    private fun onViewClick() {
        binding.materialButton.setOnClickListener { findNavController().navigateUp() }
    }

}