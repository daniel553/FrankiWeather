package com.frankiapp.domain.weather

import com.frankiapp.data.weather.IWeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FetchWeatherDataForCityUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    suspend operator fun invoke(city: String): Boolean {
        return repository.fetchWeatherDataForCity(city = city)
    }
}