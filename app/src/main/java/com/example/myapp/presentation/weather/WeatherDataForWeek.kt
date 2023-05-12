package com.example.myapp.presentation.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R
import com.example.myapp.presentation.WeatherState

@Composable
fun WeatherDataForWeek(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    for(i in 1..6){
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = colorResource(R.color.medium_blue), thickness = 1.dp)
        Spacer(modifier = Modifier.height(15.dp))

        state.weatherDetails?.weatherDataPerDay?.get(i)?.let { data ->

            var dayOfWeek = data.get(i).time.dayOfWeek.toString().lowercase().capitalize()
            if(i == 1){
                dayOfWeek = "Tomorrow"
            }

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                Text(
                    text = ("$dayOfWeek"),
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(15.dp))

                LazyRow(content = {
                    items(data) { weatherData ->
                        // called for each hour of data for next 6 days
                        WeatherDataPerHour(
                            weatherData = weatherData,
                            modifier = Modifier
                                .height(100.dp)
                                .padding(horizontal = 15.dp)
                        )
                    }
                })
            }
        }

    }

}