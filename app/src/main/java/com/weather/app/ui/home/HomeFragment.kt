package com.example.halanchallenge.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.halanchallenge.data.model.weather.WeatherBody
import com.example.halanchallenge.utils.Constants
import com.example.halanchallenge.utils.UiStates
import com.example.halanchallenge.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeVM by viewModels()
    private lateinit var binding: ActivityLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiState.observeEvent(viewLifecycleOwner, { onLoginResponse(it) })
        initViewsClicks()
    }

    private fun initViewsClicks() {
       validation()
    }

    private fun validation() {
        viewModel.login(
            WeatherBody(
                apiToken = Constants.API_KEY,
                city = "cairo",
                days = "3",
                aqi = "no",
                alerts = "no"
            )
        )
    }

    private fun onLoginResponse(states: UiStates?) {
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

    }

    private fun viewInputs() {
    }

    private fun onNoConnection() {

    }

    private fun onNotFound() {

    }

    private fun onSuccess() {
        viewInputs()
    }

}