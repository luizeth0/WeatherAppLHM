package com.challenge.weatherapplhm.di

import android.content.Context
import androidx.room.Room
import com.challenge.weatherapplhm.data.database.WeatherDAO
import com.challenge.weatherapplhm.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            "weather-db"
        ).build()

    @Provides
    fun providesDao(
        weatherDatabase: WeatherDatabase
    ): WeatherDAO = weatherDatabase.getWeatherDao()


}