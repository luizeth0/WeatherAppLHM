package com.challenge.weatherapplhm.domain

import com.challenge.weatherapplhm.data.rest.WeatherRepository
import com.challenge.weatherapplhm.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CityUseCases @Inject constructor(
    private val weatherRepository: WeatherRepository
    ) {


    operator fun invoke(city: String): Flow<UIState<DomainWeather>> = flow {

        weatherRepository.getCityWeather(city).collect{
            emit(it)
        }

    }
}