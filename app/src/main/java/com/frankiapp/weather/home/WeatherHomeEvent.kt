package com.frankiapp.weather.home

sealed interface WeatherHomeEvent {
    data object Initialize : WeatherHomeEvent
}

sealed interface WeatherHomeUIEvent {
    data object OnBack : WeatherHomeUIEvent
    data object OnAdd : WeatherHomeUIEvent
}
