package com.frankiapp.data.db

import androidx.room.TypeConverter
import com.frankiapp.data.weather.local.WeatherEntityItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    fun fromArrayList(value: List<WeatherEntityItem>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<WeatherEntityItem> {
        return try {
            Gson().fromJson<List<WeatherEntityItem>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }
}

private inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)