package com.frankiapp.domain.model

import com.frankiapp.data.weather.local.WeatherEntity
import com.frankiapp.data.weather.local.WeatherEntityItem
import com.frankiapp.data.weather.local.WeatherEntityMain

data class WeatherModel(
    val id: Long,
    val cod: Int,
    val message: String,
    val name: String,
    val weather: List<WeatherItemModel>,
    val main: WeatherMainModel,
)

data class WeatherItemModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherMainModel(
    val temp: Float,
    val tempMin: Float,
    val tempMax: Float,
)

fun List<WeatherEntity>.asWeatherModelList(): List<WeatherModel> {
    return this.map { weatherEntity -> weatherEntity.asWeatherModel() }
}

fun WeatherEntity.asWeatherModel(): WeatherModel = WeatherModel(
    id = this.id,
    cod = this.cod,
    message = this.message,
    name = this.name,
    weather = this.weather?.map { item -> item.asWeatherItemModel() }
        ?: emptyList<WeatherItemModel>(),
    main = this.main?.asWeatherMainModel() ?: WeatherMainModel(0f, 0f, 0f)
)

private fun WeatherEntityItem.asWeatherItemModel(): WeatherItemModel = WeatherItemModel(
    id = this.id,
    main = this.main,
    description = this.description,
    icon = this.icon
)

private fun WeatherEntityMain.asWeatherMainModel(): WeatherMainModel = WeatherMainModel(
    temp = this.temp,
    tempMin = this.tempMin,
    tempMax = this.tempMax,
)

//Reverse

fun WeatherModel.asWeatherEntity(): WeatherEntity = WeatherEntity(
    id,
    cod,
    message,
    name,
    weather = weather.asListWeatherItemModel(),
    main = main.asWeatherEntityMain()
)

private fun List<WeatherItemModel>.asListWeatherItemModel(): List<WeatherEntityItem> = this.map {
    it.asWeatherItemEntity()
}

fun WeatherItemModel.asWeatherItemEntity(): WeatherEntityItem = WeatherEntityItem(
    id, main, description, icon
)

fun WeatherMainModel.asWeatherEntityMain(): WeatherEntityMain = WeatherEntityMain(
    temp, tempMin, tempMax
)
