package com.challenge.weatherapplhm.di

import com.challenge.weatherapplhm.data.rest.WeatherRepository
import com.challenge.weatherapplhm.data.rest.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}