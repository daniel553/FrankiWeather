package com.frankiapp.data.weather

import com.frankiapp.data.weather.local.IWeatherLocalDatasource
import com.frankiapp.data.weather.local.WeatherLocalDatasource
import com.frankiapp.data.weather.remote.IWeatherRemoteDataSource
import com.frankiapp.data.weather.remote.WeatherRemoteDataSource
import com.frankiapp.data.weather.service.WeatherService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Weather module to provide and bind services
 */
@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {
    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}

@InstallIn(SingletonComponent::class)
abstract class WeatherModuleBinder {
    @Binds
    @Singleton
    abstract fun bindWeatherRemoteDataSource(remoteDataSource: WeatherRemoteDataSource): IWeatherRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(repository: WeatherRepository): IWeatherRepository

    @Binds
    @Singleton
    abstract fun bindWeatherLocalDataSource(localDatasource: WeatherLocalDatasource): IWeatherLocalDatasource
}