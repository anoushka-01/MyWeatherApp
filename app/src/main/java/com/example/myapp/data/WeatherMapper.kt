package com.example.myapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myapp.data.remote.WeatherDataDto
import com.example.myapp.data.remote.WeatherDto
import com.example.myapp.domain.weather.WeatherData
import com.example.myapp.domain.weather.WeatherInfo
import com.example.myapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        // maps day to the list of returned weather data for that day
        // dividing by 24 (hours per day) as there are 24 entries returned per day
        // e.g. index = 6, 6/24 = 0, = 6th hour on day 0 (today)
        // e.g. index = 26, 26/24 = 1, = 2nd hour on day 1 (tomorrow))
        it.index / 24
    }.mapValues {
        // map IndexedData to WeatherData
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherMap()
    val now = LocalDateTime.now()
    // find the weather data for the current hour at the time of request
    val currentWeatherData = weatherDataMap[0]?.find {
        // if under half past, get data for current hour
        // if over half past, get data for next hour
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}