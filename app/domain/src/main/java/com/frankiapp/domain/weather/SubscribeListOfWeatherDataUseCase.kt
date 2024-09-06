package com.frankiapp.domain.weather

import com.frankiapp.data.weather.IWeatherRepository
import com.frankiapp.domain.model.WeatherModel
import com.frankiapp.domain.model.asWeatherModelList
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class SubscribeListOfWeatherDataUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    operator fun invoke(): Flow<List<WeatherModel>> = repository.localWeatherData.map { list ->
        list.asWeatherModelList()
    }
}

