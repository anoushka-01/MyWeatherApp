package com.example.myapp.data

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.myapp.domain.IGetLocation
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.jar.Manifest
import javax.inject.Inject
import kotlin.coroutines.resume


class GetLocation @Inject constructor(
    private val locationProvider: FusedLocationProviderClient,
    private val application: Application
): IGetLocation {

    override suspend fun getLocation(): Location? {
        // check location permissions
        val checkFineLocationAccess = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val checkCoarseLocationAccess = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val checkGpsAccess =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )

        if (!checkCoarseLocationAccess || !checkFineLocationAccess || !checkGpsAccess) {
            return null
        }


        return suspendCancellableCoroutine { continuation ->
            locationProvider.lastLocation.apply {
                // check if last location is already known
                if (isComplete) {
                    if (isSuccessful) {
                        continuation.resume(result)
                    } else {
                        continuation.resume(null)
                    }
                    // return to avoid resuming more than once
                    return@suspendCancellableCoroutine
                }

                addOnSuccessListener {
                    continuation.resume(it)
                }
                addOnFailureListener{
                    continuation.resume(null)
                }
                addOnCanceledListener {
                    continuation.cancel()
                }
            }
        }
    }
}