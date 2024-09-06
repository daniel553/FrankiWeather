package com.frankiapp.weather.home.nav

sealed class WeatherRouter(val destination: String) {
    data object HomeScreen : WeatherRouter("Home")
    data object AddScreen : WeatherRouter("Add")
}