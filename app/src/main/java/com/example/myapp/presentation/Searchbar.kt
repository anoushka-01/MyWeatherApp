package com.example.myapp.presentation

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.myapp.R
import com.example.myapp.domain.ApiResponse
import com.example.myapp.domain.IWeatherRepository
import javax.inject.Inject

@Composable
fun Searchbar(
    state: WeatherState,
    modifier: Modifier = Modifier
){
    val contextForToast = LocalContext.current

    var open by remember{ mutableStateOf(false) }
    val locations = listOf("London", "Edinburgh", "New York")
    var selectedLocation by remember{ mutableStateOf("") }
    var searchSize by remember{ mutableStateOf(Size.Zero) }

    val icon = if(open) {
        // if dropdown open
        Icons.Filled.KeyboardArrowUp
    }
    else{
        // if dropdown closed
        Icons.Filled.KeyboardArrowDown
    }

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        OutlinedTextField(
            value = selectedLocation,
            onValueChange = { selectedLocation = it },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(R.color.medium_blue),
                unfocusedBorderColor = colorResource(R.color.medium_blue),
                unfocusedLabelColor = colorResource(R.color.light_blue),
                focusedLabelColor = colorResource(R.color.light_blue),
                cursorColor = Color.White,
                textColor = Color.White
            ),
            label = {Text(text = "Select Location")},
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    searchSize = coordinates.size.toSize()
                },
//            trailingIcon = {Icon(icon, "", Modifier.clickable { open = !open } )}
            trailingIcon = {Icon(icon, "", Modifier.clickable { open = !open }, tint = Color.White ) }
        )

        DropdownMenu(
            expanded = open,
            onDismissRequest = { open = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { searchSize.width.toDp() })
                .background(colorResource(R.color.navy_blue))
        ) {
            locations.forEach{ label ->
                DropdownMenuItem( onClick = {
                    Toast
                        .makeText(contextForToast, "$label selected", Toast.LENGTH_SHORT)
                        .show()
//                    getLocationInfo(label)
                    selectedLocation = label
                    open = false
                }){
                    Text(
                        text = label,
                        color = Color.White
                    )
                }
            }
        }
    }
}


fun getLocationInfo(label: String){
    Log.d("abcd", "$label")
    GetChosenLocationInfo(label)

}





// dropdown menu with some cities
// on click, call api with corresponding lat/long coords for the city


// new york lat - 40.7128, long - 74.0060
// london lat - 51.5072, long - 0.1276
// edinburgh lat - 55.9533, long - 3.1883