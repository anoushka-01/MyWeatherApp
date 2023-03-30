package com.example.myapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.domain.ApiResponse
import com.example.myapp.domain.IGetLocation
import com.example.myapp.domain.IWeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: IWeatherRepository,
    private val location: IGetLocation
): ViewModel() {

    // public getter for state, but only private setter
    var state by mutableStateOf(WeatherState())
        private set

    fun showWeatherDetails() {
        viewModelScope.launch {
            state = state.copy( isLoading = true, error = null )

            // if location is not null, then use it to make a call to the API
            if(location.getLocation() != null) {
                when (val result =
                    weatherRepository.getWeather(location.getLocation()!!.latitude, location.getLocation()!!.longitude))
                {
                    // if api call successful
                    is ApiResponse.Success -> state =
                        state.copy(weatherDetails = result.data, isLoading = false, error = null)
                    // if api call unsuccessful
                    is ApiResponse.Error -> state = state.copy(
                        weatherDetails = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }

            // if location is null, show error state
            else{
                state.copy(isLoading = false, error = "Unable to find location")
            }
        }
    }

    fun showWeatherForLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            state = state.copy( isLoading = true, error = null )

            // make a call to the API for the selected location
            when (val result =
                weatherRepository.getWeather(latitude, longitude))
            {
                // if api call successful
                is ApiResponse.Success -> state =
                    state.copy(weatherDetails = result.data, isLoading = false, error = null)
                // if api call unsuccessful
                is ApiResponse.Error -> state = state.copy(
                    weatherDetails = null,
                    isLoading = false,
                    error = result.message
                )
            }
        }
    }

}
















//            location.getLocation()?.let { location ->
//                // if location is not null, then use it to make a call to the API
//                when (val result =
//                    weatherRepository.getWeather(location.latitude, location.longitude)) {
//                    // if api call successful
//                    is ApiResponse.Success -> state =
//                        state.copy(weatherDetails = result.data, isLoading = false, error = null)
//                    // if api call unsuccessful
//                    is ApiResponse.Error -> state = state.copy(
//                        weatherDetails = null,
//                        isLoading = false,
//                        error = result.message
//                    )
//                }
//
//            // execute if location is null
//            }?: kotlin.run {
//                state.copy(isLoading = false, error = "Unable to find location")
//            }