package com.challenge.weatherapplhm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.weatherapplhm.data.database.WeatherTable
import com.challenge.weatherapplhm.data.rest.WeatherRepository
import com.challenge.weatherapplhm.domain.CityUseCases
import com.challenge.weatherapplhm.domain.DomainWeather
import com.challenge.weatherapplhm.domain.LocationUseCases
import com.challenge.weatherapplhm.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val cityUseCases: CityUseCases,
    private val locationUseCases: LocationUseCases,
    private val localRepository: WeatherRepository
) : ViewModel() {

    private val _weather: MutableStateFlow<UIState<DomainWeather>> =
        MutableStateFlow(UIState.LOADING)
    val weather: StateFlow<UIState<DomainWeather>> get() = _weather.asStateFlow()

    private val _city: MutableStateFlow<String> = MutableStateFlow("")
    val city: StateFlow<String> get() = _city.asStateFlow()

    private val _weatherStored: MutableStateFlow<UIState<WeatherTable>> =
        MutableStateFlow(UIState.LOADING)
    val weatherStored: StateFlow<UIState<WeatherTable>> get() = _weatherStored.asStateFlow()



    fun setCity(cityName: String) = viewModelScope.launch {
        _city.value = cityName
    }


    fun getWeatherCity(cityName: String) = viewModelScope.launch {
        cityUseCases.invoke(city = cityName).collect {
            _weather.value = it
        }

    }

    fun getWeatherLocation(lat: Double, lon: Double) = viewModelScope.launch {
        locationUseCases.invoke(lat = lat, lon = lon).collect {
            _weather.value = it
        }

    }

    fun getWeatherStored() {
        viewModelScope.launch {
            _weatherStored.value = localRepository.getWeather()
        }
    }

    fun saveWeather(weather: DomainWeather? = null) {
        weather?.let {
            viewModelScope.launch {
                localRepository.insertWeather(weather)
            }
        }
    }


}