package com.example.myapp.presentation

import androidx.activity.ComponentActivity
import androidx.activity.viewModels

class GetLocationData(latitude: Double, longitude: Double) : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

//    weatherViewModel.showWeatherForLocation(latitude, longitude)

//    fun callViewModel(latitude: Double, longitude: Double) {
//        weatherViewModel.showWeatherForLocation(latitude, longitude)
//    }


}