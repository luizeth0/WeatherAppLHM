package com.challenge.weatherapplhm.data.rest

import com.challenge.weatherapplhm.data.database.WeatherDAO
import com.challenge.weatherapplhm.data.database.WeatherTable
import com.challenge.weatherapplhm.data.database.toDomainTable
import com.challenge.weatherapplhm.domain.DomainWeather
import com.challenge.weatherapplhm.domain.mapToDomainItems
import com.challenge.weatherapplhm.utils.UIState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface WeatherRepository {
    fun getCityWeather(city: String?): Flow<UIState<DomainWeather>>
    fun getLocationWeather(lat: Double, lon: Double): Flow<UIState<DomainWeather>>
    // Local
    suspend fun insertWeather(weather: DomainWeather)
    suspend fun deleteWeather(weather: WeatherTable)
    suspend fun getWeather(): UIState<WeatherTable>
}

class WeatherRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val ioDispatcher: CoroutineDispatcher,
    private val weatherDAO: WeatherDAO
) : WeatherRepository {

    override fun getCityWeather(city: String?): Flow<UIState<DomainWeather>> = flow {
        city?.let {
            emit(UIState.LOADING)

            try {
                val response = serviceApi.getCityWeather(city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(UIState.SUCCESS(it.mapToDomainItems()))
                    } ?: throw Exception("Response is null")
                } else {
                    throw Exception("Failure response")
                }
            } catch (e: Exception) {
                emit(UIState.ERROR(e))
            }
        }
    }.flowOn(ioDispatcher)

    override fun getLocationWeather(lat: Double, lon: Double): Flow<UIState<DomainWeather>> = flow {

        emit(UIState.LOADING)

        try {
            val response = serviceApi.getLocationWeather(lat, lon)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.mapToDomainItems()))
                } ?: throw Exception("Response is null")
            } else {
                throw Exception("Failure response")
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }

    }.flowOn(ioDispatcher)


    //Local
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