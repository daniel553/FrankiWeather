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
    val id: Int = 0,
    val main: String = "",
    val description: String = "",
    val icon: String = "",
)

data class WeatherCityMain(
    val temp: Float = 0.0f,
    val tempMin: Float = 0.0f,
    val tempMax: Float = 0.0f,
)

//Transform

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
    WeatherCityItem(id, main, description, icon)

fun WeatherMainModel.asWeatherCityMain(): WeatherCityMain = WeatherCityMain(temp, tempMin, tempMax)


// Reverse

fun WeatherCity.asWeatherModel(): WeatherModel = WeatherModel(
    id = id,
    cod = cod,
    message = message,
    name = name,
    weather = weather.asWeatherItemModelList(),
    main = main.asWeatherMainModel()
)

fun WeatherCityMain.asWeatherMainModel(): WeatherMainModel =
    WeatherMainModel(temp, tempMin, tempMax)

fun List<WeatherCityItem>.asWeatherItemModelList(): List<WeatherItemModel> =
    this.map { it.asWeatherItemModel() }

fun WeatherCityItem.asWeatherItemModel(): WeatherItemModel =
    WeatherItemModel(id, main, description, icon)