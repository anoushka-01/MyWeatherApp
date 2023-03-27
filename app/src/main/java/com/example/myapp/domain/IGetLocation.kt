package com.example.myapp.domain

import android.location.Location

interface IGetLocation {
    suspend fun getLocation(): Location?
}