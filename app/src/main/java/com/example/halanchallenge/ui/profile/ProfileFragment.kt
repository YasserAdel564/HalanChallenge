package com.example.halanchallenge.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.halanchallenge.R
import com.example.halanchallenge.data.model.product.ProductInfo
import com.example.halanchallenge.data.storage.local.PreferencesHelper
import com.example.halanchallenge.databinding.ActivityProductsListBinding
import com.example.halanchallenge.ui.SharedVM
import com.example.halanchallenge.utils.UiStates
import com.example.halanchallenge.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileVM by viewModels()
    private lateinit var binding: ActivityProductsListBinding
    private val sharedVM: SharedVM by activityViewModels()
    private val adapter = ProductsAdapter()

    @Inject
    lateinit var helper: PreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observeEvent(viewLifecycleOwner, { onResponse(it) })
        viewModel.getProducts()
        fillUserInfo()
        setupRV()
        onViewClick()
    }

    private fun fillUserInfo() {
        sharedVM.userLiveData.observe(viewLifecycleOwner, {
            val userInfo = it
            binding.emailTv.text = userInfo.email
            binding.usernameTv.text = userInfo.username
            binding.phoneNumberTv.text = userInfo.phone
            Glide.with(this).load(it.image).into(binding.userIv)
        })
    }

    private fun onViewClick() {
        binding.logoutIV.setOnClickListener { logout() }
        adapter.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                val product = adapter.data[position] as ProductInfo
                sharedVM.setProductLiveData(product)
                goToProductDetails()
            }
    }

    private fun setupRV() {
        binding.productsListRv.adapter = adapter
    }

    private fun onResponse(states: UiStates?) {
        when (states) {
            UiStates.Loading -> {
                onLoading()
            }
            UiStates.Success -> {
                onSuccess()
            }
            UiStates.Empty -> {
                viewInputs()
            }
            UiStates.Error -> {
                viewInputs()
            }
            UiStates.NotFound -> {
                onNotFound()
            }
            UiStates.NoConnection -> {
                onNoConnection()
            }
        }
    }

    private fun onLoading() {
        binding.viewContainer.visibility = View.GONE
        binding.loadingLayout.root.visibility = View.VISIBLE
        binding.loadingLayout.loading.visibility = View.VISIBLE
    }

    private fun viewInputs() {
        binding.viewContainer.visibility = View.VISIBLE
        binding.loadingLayout.root.visibility = View.GONE
    }

    private fun onNoConnection() {
        binding.viewContainer.visibility = View.GONE
        binding.loadingLayout.root.visibility = View.VISIBLE
        binding.loadingLayout.loading.visibility = View.GONE
        binding.loadingLayout.noConnection.visibility = View.VISIBLE
    }

    private fun onNotFound() {
        binding.viewContainer.visibility = View.GONE
        binding.loadingLayout.root.visibility = View.VISIBLE
        binding.loadingLayout.loading.visibility = View.GONE
        binding.loadingLayout.error.visibility = View.VISIBLE
    }

    private fun onSuccess() {
        adapter.replaceData(viewModel.products)
        viewInputs()
    }

    private fun goToProductDetails() {
        findNavController().navigate(R.id.action_profile_fragment_to_product_details_fragment)
    }

    private fun logout() {
        helper.clearPreferences()
        findNavController().navigateUp()
    }


}