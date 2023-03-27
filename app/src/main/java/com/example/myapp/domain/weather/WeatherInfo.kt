package com.example.myapp.domain.weather


data class WeatherInfo(
    // maps the current day index to the weather data for that day
    // e.g. index = 0 returns weather for today, index = 1 returns weather for tomorrow, etc.
    val weatherDataPerDay :  Map<Int, List<WeatherData>>,

    // weather data for the current time
    val currentWeatherData: WeatherData?
)
