package com.frankiapp.weather.model

data class WeatherCity(
    val id: Long = 0,
    val cod: Int = 0,
    val message: String = "",
    val name: String = "",
    val weather: List<WeatherCityItem> = emptyList(),
    val main: WeatherCityMain = WeatherCityMain(),
)

data class WeatherCityItem(
    val main: String = "",
    val description: String = "",
    val icon: String = "",
)

data class WeatherCityMain(
    val temp: Float = 0.0f,
    val tempMin: Float = 0.0f,
    val tempMax: Float = 0.0f,
)
