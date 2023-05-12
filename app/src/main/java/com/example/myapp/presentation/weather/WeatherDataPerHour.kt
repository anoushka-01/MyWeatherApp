package com.example.myapp.presentation.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapp.R
import com.example.myapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDataPerHour(
    weatherData: WeatherData,
    modifier: Modifier = Modifier
) {
    // "remember" - means data will only be re-fetched when weather data changes,
    // not every time the hourly data is updated
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }

    Card(
        backgroundColor = colorResource(R.color.dark_blue),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(2.dp)
    ) {

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formattedTime,
                color = Color.LightGray
            )
            Image(
                painter = painterResource(id = weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(40.dp)
            )
            Text(
                text = "${weatherData.temperatureCelsius}°C",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

    }

}