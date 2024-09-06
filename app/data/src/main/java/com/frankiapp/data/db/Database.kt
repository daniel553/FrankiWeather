package com.frankiapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.frankiapp.data.weather.local.WeatherDao
import com.frankiapp.data.weather.local.WeatherEntity


@Database(entities = [WeatherEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    companion object {
        const val name = "app.db"
    }

    //DAOs
    abstract fun weatherDao(): WeatherDao
}