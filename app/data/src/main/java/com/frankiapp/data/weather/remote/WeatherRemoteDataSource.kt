package com.frankiapp.data.weather.remote

import com.frankiapp.data.weather.service.WeatherResponse
import com.frankiapp.data.weather.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface IWeatherRemoteDataSource {
    /**
     * GET weather details for a certain city
     * @param city name, ie: Los Angeles
     */
    suspend fun getWeatherForCity(city: String): WeatherRemoteData
}

@Singleton
class WeatherRemoteDataSource @Inject constructor(
    private val service: WeatherService
) : IWeatherRemoteDataSource {

    override suspend fun getWeatherForCity(city: String): WeatherRemoteData =
        withContext(Dispatchers.IO) {
            return@withContext try {
                service.getWeatherForCity(city).asWeatherData()
            } catch (e: Exception) {
                WeatherRemoteData.Error(e, e.message)
            }
        }
}
