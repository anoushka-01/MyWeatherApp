package com.example.myapp.domain

import com.example.myapp.domain.weather.WeatherDetails

interface IWeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): ApiResponse<WeatherDetails>
}