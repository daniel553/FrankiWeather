package com.frankiapp.data.weather

import com.frankiapp.data.weather.local.IWeatherLocalDatasource
import com.frankiapp.data.weather.local.WeatherEntity
import com.frankiapp.data.weather.remote.IWeatherRemoteDataSource
import com.frankiapp.data.weather.remote.WeatherRemoteData
import com.frankiapp.data.weather.service.asWeatherEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface IWeatherRepository {
    val localWeatherData: Flow<List<WeatherEntity>>
    suspend fun fetchWeatherDataForCity(city: String): WeatherEntity?
    suspend fun saveWeatherEntity(weatherEntity: WeatherEntity): Boolean
}

@Singleton
class WeatherRepository @Inject constructor(
    private val remoteDataSource: IWeatherRemoteDataSource,
    private val localDatasource: IWeatherLocalDatasource,
) : IWeatherRepository {

    override val localWeatherData: Flow<List<WeatherEntity>>
        get() = localDatasource.all

    override suspend fun fetchWeatherDataForCity(city: String): WeatherEntity? {
        return when (val remoteData = remoteDataSource.getWeatherForCity(city)) {
            is WeatherRemoteData.Success -> remoteData.weatherResponse.asWeatherEntity()
            is WeatherRemoteData.Error -> {
                /** Log errors, etc*/
                null
            }
        }
    }

    override suspend fun saveWeatherEntity(weatherEntity: WeatherEntity): Boolean {
        return saveLocalData(weatherEntity)
    }

    private suspend fun saveLocalData(weatherEntity: WeatherEntity): Boolean {
        return try {
            localDatasource.insert(weatherEntity)
            true
        } catch (e: Exception) {
            false
        }
    }

}


