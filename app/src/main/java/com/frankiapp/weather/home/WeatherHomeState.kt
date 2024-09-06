package com.frankiapp.weather.home

import com.frankiapp.weather.model.WeatherCity

sealed interface WeatherHomeState {
    data object Loading : WeatherHomeState
    data class Success(val cities: List<WeatherCity>) : WeatherHomeState
}


