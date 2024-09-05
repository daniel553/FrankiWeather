package com.frankiapp.data.weather.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun all(): Flow<List<WeatherEntity>>

    @Query("SELECT * FROM weather")
    suspend fun getAll(): List<WeatherEntity>

    @Query("SELECT * FROM weather WHERE id = :id")
    suspend fun getById(id: Long): WeatherEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: WeatherEntity): Long

    @Delete
    suspend fun delete(entity: WeatherEntity)
}