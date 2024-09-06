package com.frankiapp.weather.home.addcity

import com.frankiapp.weather.model.WeatherCity

data class AddCityState(
    val search: String = "",
    val searching: Boolean = false,
    val city: WeatherCity? = null,
    val error: AddCityErrorMessages? = null,
)
