package com.challenge.weatherapplhm.domain

import com.challenge.weatherapplhm.data.rest.WeatherRepository
import com.challenge.weatherapplhm.utils.UIState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationUseCases @Inject constructor(private val weatherRepository: WeatherRepository) {


    operator fun invoke(lat: Double, lon: Double): Flow<UIState<DomainWeather>> = flow {

        weatherRepository.getLocationWeather(lat, lon).collect{
            emit(it)
        }

    }
}