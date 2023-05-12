package com.example.myapp.presentation


fun GetChosenLocationInfo(label: String): GetLocationData {

    // gets the latitude and longitude for the chosen location
    // pass these details onto another function which will make the api call

    return when(label){
        "London" -> GetLocationData(51.5072, 0.1276)
        "Edinburgh" -> GetLocationData(55.9533, 3.1883)
        "New York" -> GetLocationData(40.7128, 74.0060)
        else -> GetLocationData(0.0,0.0)
    }

}