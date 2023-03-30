package com.example.myapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels

fun GetChosenLocationInfo(label: String) {

    // gets the latitude and longitude for the chosen location
    // pass these details onto another function which will make the api call
    when(label){
        "London" -> GetLocationData(51.5072, 0.1276)
        "Edinburgh" -> GetLocationData(55.9533, 3.1883)
        "New York" -> GetLocationData(40.7128, 74.0060)
    }

}