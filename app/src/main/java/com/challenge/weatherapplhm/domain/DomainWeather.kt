package com.challenge.weatherapplhm.domain

import com.challenge.weatherapplhm.data.model.*

data class DomainWeather(
    val base: String? = null,
    val clouds: Clouds? = null,
    val cod: Int? = null,
    val coord: Coord? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val main: Main? = null,
    val name: String? = null,
    val sys: Sys? = null,
    val timezone: Int? = null,
    val visibility: Int? = null,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
)

fun WeatherResponse?.mapToDomainItems(): DomainWeather =
        DomainWeather(
            base = this?.base,
            clouds = this?.clouds,
            cod = this?.cod,
            coord = this?.coord,
            dt = this?.dt,
            id = this?.id,
            main = this?.main,
            name = this?.name,
            sys = this?.sys,
            timezone = this?.timezone,
            visibility = this?.visibility,
            weather = this?.weather,
            wind = this?.wind
        )

