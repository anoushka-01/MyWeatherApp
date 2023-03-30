package com.example.myapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapp.R
import com.example.myapp.presentation.weather.WeatherDataForWeek
import com.example.myapp.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var getPermission: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ask user for location permissions
        getPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            weatherViewModel.showWeatherDetails()
        }
        getPermission.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))

        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(R.color.navy_blue))
                    ){
                        Spacer(modifier = Modifier.height(3.dp))
                        Searchbar(weatherViewModel.state)
                        WeatherData(state = weatherViewModel.state)
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorResource(R.color.navy_blue))
                                .verticalScroll(rememberScrollState())
                        ){
                            WeatherDataForToday(state = weatherViewModel.state)
                            WeatherDataForWeek(state = weatherViewModel.state)
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                    }

                }
            }
        }
    }
}
