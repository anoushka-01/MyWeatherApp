package com.example.myapp.presentation

import com.example.myapp.domain.weather.WeatherDetails

data class WeatherState(
    val weatherDetails: WeatherDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
