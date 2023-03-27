package com.example.myapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.data.remote.IWeatherApi
import com.example.myapp.domain.ApiResponse
import com.example.myapp.domain.IWeatherRepository
import com.example.myapp.domain.weather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImplementation @Inject constructor(
    private val api: IWeatherApi
): IWeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeather(latitude: Double, longitude: Double): ApiResponse<WeatherInfo> {
         return try {
             ApiResponse.Success(
                 data = api.getWeather(
                     latitude = latitude,
                     longitude = longitude
                 ).toWeatherInfo()
             )
         }
         catch (e: Exception){
             e.printStackTrace()
             ApiResponse.Error(e.message ?: "Unknown error")
         }
    }
}