package com.example.myapp.di

import com.example.myapp.data.GetLocation
import com.example.myapp.data.WeatherRepositoryImplementation
import com.example.myapp.domain.IGetLocation
import com.example.myapp.domain.IWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class Modules {

    @Binds
    @Singleton
    abstract fun bindGetLocation(getLocation: GetLocation): IGetLocation

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImplementation: WeatherRepositoryImplementation): IWeatherRepository
}