package com.frankiapp.weather.home.addcity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frankiapp.domain.weather.FetchWeatherDataForCityUseCase
import com.frankiapp.domain.weather.ProvideCitySuggestionsUseCase
import com.frankiapp.domain.weather.SaveWeatherCityUseCase
import com.frankiapp.weather.model.WeatherCity
import com.frankiapp.weather.model.asWeatherCity
import com.frankiapp.weather.model.asWeatherModel
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
    private val saveWeatherCityUseCase: SaveWeatherCityUseCase,
    private val provideCitySuggestionsUseCase: ProvideCitySuggestionsUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<AddCityState> = MutableStateFlow(AddCityState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent: MutableSharedFlow<AddCityUIEvent> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        onEvent(AddCityEvent.OnSearchChanged(""))
    }

    fun onEvent(event: AddCityEvent) {
        when (event) {
            is AddCityEvent.OnSearchChanged -> handleOnSearchChanged(event.search)
            is AddCityEvent.OnSearchCity -> handleOnSearchCity(event.search)
            is AddCityEvent.OnAddCity -> handleAddCity(event.city)
        }
    }

    private fun handleAddCity(city: WeatherCity) {
        viewModelScope.launch {
            val saved = saveWeatherCityUseCase.invoke(city.asWeatherModel())
            if (saved) {
                _uiEvent.emit(AddCityUIEvent.OnMessage(AddCityErrorMessages.Saved))
                _uiEvent.emit(AddCityUIEvent.OnBack)
            } else {
                _uiEvent.emit(AddCityUIEvent.OnMessage(AddCityErrorMessages.NotSaved))
            }
        }
    }

    private fun handleOnSearchCity(search: String) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(searching = true) }
            //Use case
            val city = fetchWeatherDataForCityUseCase.invoke(city = search)
            if (city == null) {
                _uiEvent.emit(AddCityUIEvent.OnMessage(AddCityErrorMessages.NotFound))
            } else {
                _uiState.update { state -> state.copy(city = city.asWeatherCity()) }
            }
            _uiState.update { state -> state.copy(searching = false) }
        }
    }

    private fun handleOnSearchChanged(search: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    search = search,
                    suggestions = provideCitySuggestionsUseCase.invoke(search)
                )
            }
        }
    }

}

enum class AddCityErrorMessages {
    NotFound,
    Saved,
    NotSaved,
}

