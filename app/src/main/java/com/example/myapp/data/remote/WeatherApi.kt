package com.example.myapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    // function to call API endpoint

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
    ): WeatherDto
}