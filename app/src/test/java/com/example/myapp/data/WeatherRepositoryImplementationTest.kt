package com.example.myapp.data

import com.example.myapp.CoroutineTestRules
import com.example.myapp.data.remote.IWeatherApi
import com.example.myapp.data.remote.WeatherDataDto
import com.example.myapp.data.remote.WeatherDto
import com.example.myapp.domain.ApiResponse
import com.example.myapp.domain.weather.WeatherData
import com.example.myapp.domain.weather.WeatherType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplementationTest {

    private val api: IWeatherApi = mockk()

    private lateinit var weatherRepositoryImplementation: WeatherRepositoryImplementation

    @get:Rule
    val coroutineTestRule = CoroutineTestRules()

    @Before
    fun setUp() {
        weatherRepositoryImplementation = WeatherRepositoryImplementation(api)
    }

    @Test
    fun `GIVEN latitude and longitude WHEN api response fails THEN api response error should be called`() = runTest(coroutineTestRule.testDispatcher){
        coEvery { api.getWeather(0.0,0.0) } throws Exception()

        val apiResponse = weatherRepositoryImplementation.getWeather(0.0,0.0)

        expectThat(apiResponse is ApiResponse.Error).isTrue()
    }



    @Test
    fun `GIVEN latitude and longitude WHEN api response succeeds THEN api response success should be called`() = runTest(coroutineTestRule.testDispatcher){

        val expectedResponse = WeatherDataDto(
            time = listOf("2023-06-02T08:00", "2023-06-02T09:00", "2023-06-02T10:00"),
            temperatures = listOf(25.5, 26.2, 24.8),
            weatherCodes = listOf(200, 201, 202),
            pressures = listOf(1012.5, 1013.2, 1011.8),
            windSpeeds = listOf(8.5, 9.2, 7.8),
            humidities = listOf(60.0, 62.5, 58.3)
        )

        coEvery { api.getWeather(0.0,0.0) } returns WeatherDto(expectedResponse)

        val apiResponse = weatherRepositoryImplementation.getWeather(0.0,0.0)

        expectThat(apiResponse is ApiResponse.Success).isTrue()
    }

}
