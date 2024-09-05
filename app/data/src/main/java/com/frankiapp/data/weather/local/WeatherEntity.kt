package com.frankiapp.data.weather.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey
    val id: Long,
    val cod: Int,
    val message: String? = "",
    val name: String? = "",
    val weather: List<WeatherEntityItem>? = null,
    @Embedded
    val main: WeatherEntityMain? = null
)

data class WeatherEntityItem(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherEntityMain(
    val temp: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
)