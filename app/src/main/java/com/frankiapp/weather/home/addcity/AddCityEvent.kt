package com.frankiapp.weather.home.addcity

import com.frankiapp.weather.model.WeatherCity

sealed interface AddCityEvent {
    data class OnSearchChanged(val search: String) : AddCityEvent
    data class OnSearchCity(val search: String) : AddCityEvent
    data class OnAddCity(val city: WeatherCity) : AddCityEvent
}

sealed interface AddCityUIEvent {
    data object OnBack : AddCityUIEvent
    data class OnMessage(val message: AddCityErrorMessages) : AddCityUIEvent
}
