package com.example.reviewalimenticio.viewModel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.example.reviewalimenticio.models.Refeicao

class TelaAdicionarRefeicaoViewModel {
    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager
    private lateinit var context: Context
    private val MIN_TIME_BETWEEN_UPDATES: Long = 1000
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10.0f

    fun setupContext(context: Context) {
        this.context = context
    }

    fun startLocationUpdates() {
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

            override fun onProviderEnabled(provider: String) {}

            override fun onProviderDisabled(provider: String) {}
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            MIN_TIME_BETWEEN_UPDATES,
            MIN_DISTANCE_CHANGE_FOR_UPDATES,
            locationListener
        )
    }

    fun stopLocationUpdates() {
        locationManager.removeUpdates(locationListener)
    }
}