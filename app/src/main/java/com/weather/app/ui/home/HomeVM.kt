package com.example.halanchallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blankj.utilcode.util.NetworkUtils
import com.example.halanchallenge.data.model.weather.WeatherBody
import com.example.halanchallenge.repos.weather.WeatherRepo
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
class HomeVM @Inject
constructor(
    private val repository: WeatherRepo
) : ViewModel() {

    private var job: Job? = null
    private var _uiState = MutableLiveData<Event<UiStates>>()
    val uiState: LiveData<Event<UiStates>>
        get() = _uiState


    fun login(loginBody: WeatherBody) {
        if (NetworkUtils.isConnected()) {
            if (job?.isActive == true)
                return
            job = launchJob(loginBody)
        } else {
            _uiState.value = Event(UiStates.NoConnection)
        }
    }

    private fun launchJob(loginBody: WeatherBody): Job {
        _uiState.value = Event(UiStates.Loading)
        return CoroutineScope(Dispatchers.Main).launch {
            when (val response = repository.getWeather(loginBody)) {
                is DataResource.Success -> {
                    val loginResponse = response.value
//                    if (loginResponse.status.equals("OK")) {
//                        helper.token = loginResponse.token
//                        userInfo = loginResponse.userInfo
                    _uiState.value = Event(UiStates.Success)
//                    } else
                    _uiState.value = Event(UiStates.Error)

                }
                is DataResource.Error -> {
                    _uiState.value = Event(UiStates.NotFound)
                }
            }
        }
    }
}