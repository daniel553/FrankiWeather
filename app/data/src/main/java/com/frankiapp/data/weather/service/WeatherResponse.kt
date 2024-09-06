package com.frankiapp.data.weather.service

import com.frankiapp.data.weather.local.WeatherEntity
import com.frankiapp.data.weather.local.WeatherEntityItem
import com.frankiapp.data.weather.local.WeatherEntityMain
import com.google.gson.annotations.SerializedName

/**
 * JSON Example:
 * {
 *   "coord": {
 *     "lon": -118.2437,
 *     "lat": 34.0522
 *   },
 *   "weather": [
 *     {
 *       "id": 800,
 *       "main": "Clear",
 *       "description": "clear sky",
 *       "icon": "01n"
 *     }
 *   ],
 *   "base": "stations",
 *   "main": {
 *     "temp": 302.93,
 *     "feels_like": 302.28,
 *     "temp_min": 297.64,
 *     "temp_max": 305.55,
 *     "pressure": 1012,
 *     "humidity": 37,
 *     "sea_level": 1012,
 *     "grnd_level": 993
 *   },
 *   "visibility": 10000,
 *   "wind": {
 *     "speed": 3.6,
 *     "deg": 250
 *   },
 *   "clouds": {
 *     "all": 0
 *   },
 *   "dt": 1725510932,
 *   "sys": {
 *     "type": 2,
 *     "id": 2002814,
 *     "country": "US",
 *     "sunrise": 1725456551,
 *     "sunset": 1725502490
 *   },
 *   "timezone": -25200,
 *   "id": 5368361,
 *   "name": "Los Angeles",
 *   "cod": 200,
 *   "message":"city not found"
 * }
 */
data class WeatherResponse(
    val id: Long? = 0L,
    val cod: Int,
    val message: String? = "",
    val name: String? = "",
    val weather: List<WeatherResponseItem>? = null,
    val main: WeatherResponseMain? = null
)

data class WeatherResponseItem(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherResponseMain(
    val temp: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
)

fun WeatherResponse.asWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        id = this.id ?: 0,
        cod = this.cod,
        message = this.message,
        name = name,
        weather = weather?.map { w ->
            WeatherEntityItem(
                w.id, w.main, w.description, w.icon
            )
        }.orEmpty(),
        main = WeatherEntityMain(
            temp = this.main?.temp ?: 0.0f,
            tempMax = this.main?.tempMax ?: 0.0f,
            tempMin = this.main?.tempMin ?: 0.0f,
        )
    )
}