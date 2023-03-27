package com.example.myapp.domain

import com.example.myapp.domain.weather.WeatherInfo

interface IWeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): ApiResponse<WeatherInfo>
}