package com.frankiapp.data.weather.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface IWeatherLocalDatasource {
    val all: Flow<List<WeatherEntity>>
    suspend fun getAll(): List<WeatherEntity>
    suspend fun getById(id: Long): WeatherEntity
    suspend fun insert(entity: WeatherEntity): Long
    suspend fun delete(entity: WeatherEntity)
}

@Singleton
class WeatherLocalDatasource @Inject constructor(
    private val dao: WeatherDao
) : IWeatherLocalDatasource {
    override val all: Flow<List<WeatherEntity>>
        get() = dao.all()

    override suspend fun getAll(): List<WeatherEntity> =
        withContext(Dispatchers.IO) { dao.getAll() }

    override suspend fun getById(id: Long): WeatherEntity =
        withContext(Dispatchers.IO) { dao.getById(id) }

    override suspend fun insert(entity: WeatherEntity): Long =
        withContext(Dispatchers.IO) { dao.insert(entity) }

    override suspend fun delete(entity: WeatherEntity) =
        withContext(Dispatchers.IO) { dao.delete(entity) }
}