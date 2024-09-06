package com.frankiapp.weather.model

import com.frankiapp.domain.model.WeatherItemModel
import com.frankiapp.domain.model.WeatherMainModel
import com.frankiapp.domain.model.WeatherModel

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


fun List<WeatherModel>.asWeatherCityList(): List<WeatherCity> = this.map { it.asWeatherCity() }

fun WeatherModel.asWeatherCity(): WeatherCity = WeatherCity(
    id = id,
    cod = cod,
    message = message,
    name = name,
    weather = weather.map { w -> w.asWeatherCityItem() },
    main = main.asWeatherCityMain()
)

fun WeatherItemModel.asWeatherCityItem(): WeatherCityItem =
    WeatherCityItem(main, description, icon)

fun WeatherMainModel.asWeatherCityMain(): WeatherCityMain = WeatherCityMain(temp, tempMin, tempMax)

