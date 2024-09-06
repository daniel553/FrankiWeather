package com.frankiapp.weather.home.addcity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frankiapp.domain.weather.FetchWeatherDataForCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCityViewModel @Inject constructor(
    private val fetchWeatherDataForCityUseCase: FetchWeatherDataForCityUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<AddCityState> = MutableStateFlow(AddCityState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent: MutableSharedFlow<AddCityUIEvent> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: AddCityEvent) {
        when (event) {
            is AddCityEvent.OnSearchChanged -> handleOnSearchChanged(event.search)
            is AddCityEvent.OnSearchCity -> handleOnSearchCity(event.search)
        }
    }

    private fun handleOnSearchCity(search: String) {
        viewModelScope.launch {
            //Use case
            //val city = fetchWeatherDataForCityUseCase.invoke(city = search)
        }
    }

    private fun handleOnSearchChanged(search: String) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(search = search) }
        }
    }

}

