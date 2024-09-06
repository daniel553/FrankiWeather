package com.frankiapp.weather.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frankiapp.domain.weather.FetchWeatherDataForCityUseCase
import com.frankiapp.domain.weather.SubscribeListOfWeatherDataUseCase
import com.frankiapp.weather.home.WeatherHomeState.Loading
import com.frankiapp.weather.home.WeatherHomeState.Success
import com.frankiapp.weather.model.asWeatherCityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val fetchWeatherDataForCityUseCase: FetchWeatherDataForCityUseCase,
    private val subscribeListOfWeatherDataUseCase: SubscribeListOfWeatherDataUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<WeatherHomeState> =
        MutableStateFlow(Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent: MutableSharedFlow<WeatherHomeUIEvent> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        onEvent(WeatherHomeEvent.Initialize)
    }

    fun onEvent(event: WeatherHomeEvent) {
        when (event) {
            WeatherHomeEvent.Initialize -> handleInitialize()
        }
    }

    private fun handleInitialize() {
        viewModelScope.launch {
            subscribeListOfWeatherDataUseCase.invoke().onEach { list ->
                if (_uiState.value is Loading) {
                    _uiState.update { Success(cities = list.asWeatherCityList()) }
                } else {
                    _uiState.update { state -> (state as Success).copy(cities = list.asWeatherCityList()) }
                }
            }.stateIn(this)
        }
    }

}

