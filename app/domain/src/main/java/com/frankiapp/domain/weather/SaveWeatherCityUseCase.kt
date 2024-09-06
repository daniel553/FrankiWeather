package com.frankiapp.domain.weather

import com.frankiapp.data.weather.IWeatherRepository
import com.frankiapp.domain.model.WeatherModel
import com.frankiapp.domain.model.asWeatherEntity
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SaveWeatherCityUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    suspend operator fun invoke(city: WeatherModel): Boolean {
        return repository.saveWeatherEntity(weatherEntity = city.asWeatherEntity())
    }
}