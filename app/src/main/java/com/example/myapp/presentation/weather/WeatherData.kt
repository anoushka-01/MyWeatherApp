package com.example.myapp.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.R
import java.time.format.DateTimeFormatter

@Composable
fun WeatherData(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherDetails?.currentWeatherData?.let { data ->
        Card(
            backgroundColor = colorResource(R.color.light_blue),
            shape = RoundedCornerShape(15.dp),
            modifier = modifier
                .padding(10.dp, 15.dp)
                .height(350.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.clouds_background),
                contentDescription = "clouds background",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Image(
                        painter = painterResource(R.drawable.ic_outline_location_on_24),
                        contentDescription = "location icon"
                    )
                    Text(
                        text = "Your Location",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    // display current time using api result
                    Text(
                        text = ("Today, ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}"),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // use weatherCode from api result to find appropriate weather icon (e.g. sunny, rain etc.)
                        Image(
                            painter = painterResource(data.weatherType.iconRes),
                            contentDescription = null,
                            modifier = Modifier.width(180.dp)
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "${data.temperatureCelsius}°C",
                            fontSize = 60.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = data.weatherType.weatherDescription,
                            fontSize = 20.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column() {
                        Card(
                            backgroundColor = colorResource(R.color.medium_blue),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, colorResource(R.color.navy_blue)),
                            modifier = modifier
                                .padding(8.dp)
                                .height(80.dp)
                                .width(100.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row() {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_wind),
                                        contentDescription = "wind icon",
                                        modifier = Modifier.size(25.dp)
                                    )
                                    Text(
                                        text = "  Wind",
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.wrapContentHeight()
                                    )
                                }
                                Text(
                                    text = "${data.windSpeed.toString()} km/h",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.wrapContentHeight()
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(1.dp))

                        Card(
                            backgroundColor = colorResource(R.color.medium_blue),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, colorResource(R.color.navy_blue)),
                            modifier = modifier
                                .padding(8.dp)
                                .height(80.dp)
                                .width(100.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row() {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_drop),
                                        contentDescription = "humidity icon",
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = " Humidity",
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.wrapContentHeight()
                                    )
                                }
                                Text(
                                    text = "${data.humidity.toString()} %",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.wrapContentHeight()
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(1.dp))

                        Card(
                            backgroundColor = colorResource(R.color.medium_blue),
                            shape = RoundedCornerShape(15.dp),
                            border = BorderStroke(2.dp, colorResource(R.color.navy_blue)),
                            modifier = modifier
                                .padding(8.dp)
                                .height(80.dp)
                                .width(100.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row() {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.ic_pressure),
                                        contentDescription = "pressure icon",
                                        modifier = Modifier.size(25.dp)
                                    )
                                    Text(
                                        text = " Pressure",
                                        color = Color.DarkGray,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.wrapContentHeight()
                                    )
                                }
                                Text(
                                    text = "${data.pressure.toString()} hpa",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.wrapContentHeight()
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
