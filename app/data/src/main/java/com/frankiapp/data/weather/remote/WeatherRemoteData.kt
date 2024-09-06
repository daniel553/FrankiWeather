package com.frankiapp.data.weather.remote

import com.frankiapp.data.weather.service.WeatherResponse

sealed interface WeatherRemoteData {
    data class Success(val weatherResponse: WeatherResponse) : WeatherRemoteData
    data class Error(val throwable: Throwable, val message: String? = null) : WeatherRemoteData
}

internal fun WeatherResponse.asWeatherData(): WeatherRemoteData = when (cod) {
    200 -> WeatherRemoteData.Success(this)
    else -> WeatherRemoteData.Error(RuntimeException("No weather data"), message)
}
