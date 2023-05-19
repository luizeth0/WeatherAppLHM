package com.challenge.weatherapplhm.data.database

import com.challenge.weatherapplhm.domain.DomainWeather
import com.challenge.weatherapplhm.utils.UIState
import javax.inject.Inject


interface LocalRepository {
    suspend fun insertWeather(weather: DomainWeather)
    suspend fun deleteWeather(weather: WeatherTable)
    suspend fun getWeather(): UIState<WeatherTable>
}

class LocalRepositoryImpl @Inject constructor(
    private val weatherDAO: WeatherDAO
) : LocalRepository {

    override suspend fun insertWeather(weather: DomainWeather) {
        try {
            val weatherTable = weather.toDomainTable()
            weatherDAO.insertWeather(weatherTable)
        } catch (e: Exception) {
            e.message
        }
    }

    override suspend fun deleteWeather(weather: WeatherTable) {
        try {
            weatherDAO.deleteWeather(weather)
        } catch (e: Exception) {
            e.message
        }
    }

    override suspend fun getWeather(): UIState<WeatherTable> {
        return try {
            UIState.SUCCESS(weatherDAO.getWeather())
        } catch (e: Exception) {
            UIState.ERROR(e)
        }
    }

}