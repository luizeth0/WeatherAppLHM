package com.challenge.weatherapplhm.data.rest

import com.challenge.weatherapplhm.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    /**
     * This methods sends an HTTP GET request to the server to retrieve data of a specified type.
     * @param city The city query to retrieve.
     * @param lat The lat query to retrieve.
     * @param lon The lon query to retrieve.
     * @param apiKey The APIKEY as unique identifier used to authenticate and authorize a user.
     * @return A `Response` object wrapping a `WeatherResponse` object.
     */
    @GET(WEATHER_PATH)
    suspend fun getCityWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = API_KEY
    ): Response<WeatherResponse>

    @GET(WEATHER_PATH)
    suspend fun getLocationWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = API_KEY
    ): Response<WeatherResponse>


    // https://api.openweathermap.org/data/2.5/weather?q=London&appid=65d00499677e59496ca2f318eb68c049
    // https://api.openweathermap.org/data/2.5/weather?lat=33.9107481&lon=-84.4814441&appid=65d00499677e59496ca2f318eb68c049
    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"
        private const val WEATHER_PATH = "data/2.5/weather"
        private const val API_KEY = "65d00499677e59496ca2f318eb68c049"
    }

}