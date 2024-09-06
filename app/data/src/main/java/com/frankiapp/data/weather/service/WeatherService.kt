package com.frankiapp.data.weather.service

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather?appid=5deca2f99f97d972562a33188d696b4a")
    suspend fun getWeatherForCity(@Query("q") city: String): WeatherResponse
}