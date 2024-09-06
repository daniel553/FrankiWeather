package com.frankiapp.weather.home.vm

import androidx.lifecycle.ViewModel
import com.frankiapp.weather.home.WeatherHomeEvent
import com.frankiapp.weather.home.WeatherHomeState
import com.frankiapp.weather.home.WeatherHomeState.Loading
import com.frankiapp.weather.home.WeatherHomeUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
) : ViewModel() {
    private val _uiState: MutableStateFlow<WeatherHomeState> =
        MutableStateFlow(Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent: MutableSharedFlow<WeatherHomeUIEvent> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: WeatherHomeEvent) {

    }

}