package com.example.halanchallenge.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.halanchallenge.R
import com.example.halanchallenge.data.model.login.LoginBody
import com.example.halanchallenge.databinding.ActivityLoginBinding
import com.example.halanchallenge.ui.SharedVM
import com.example.halanchallenge.utils.Constants.symbols
import com.example.halanchallenge.utils.UiStates
import com.example.halanchallenge.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginVM by viewModels()
    private lateinit var binding: ActivityLoginBinding
    private val sharedVM: SharedVM by activityViewModels()


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
        binding.loginButton.setOnClickListener { validation() }
    }

    private fun validation() {
        val username = binding.usernameEt.text.trim().toString()
        val password = binding.passwordEt.text.trim().toString()

        if (username.isBlank()) {
            binding.usernameEt.error = getString(R.string.required_field)
            return
        }
        if (username.any { it in symbols }) {
            binding.usernameEt.error = getString(R.string.username_validation)
            return
        }
        if (username.length < 6 || username.length > 15) {
            binding.usernameEt.error = getString(R.string.username_length)
            return
        }

        if (password.isBlank()) {
            binding.passwordEt.error = getString(R.string.required_field)
            return
        }

        if (password.length < 8) {
            binding.passwordEt.error = getString(R.string.password_validation)
            return
        }
        viewModel.login(LoginBody(username, password))
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
        viewModel.userInfo?.let { sharedVM.setUserLiveData(it) }
        viewInputs()
        findNavController().navigate(R.id.action_login_fragment_to_profile_fragment)
    }

}