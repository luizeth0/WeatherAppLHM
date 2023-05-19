package com.challenge.weatherapplhm.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.weatherapplhm.domain.DomainWeather

@Entity(tableName = "weather")
data class WeatherTable(
    @PrimaryKey
    val id: Int,
    val base: String? = null,
    val cod: Int? = null,
    val dt: Int? = null,
    val name: String? = null,
    val timezone: Int? = null,
    val visibility: Int? = null
)

fun DomainWeather.toDomainTable(): WeatherTable {
    return WeatherTable(
        this.id ?: 0,
        this.base,
        this.cod,
        this.dt,
        this.name,
        this.timezone,
        this.visibility

    )
}