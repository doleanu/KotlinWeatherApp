package com.example.weatherapp.data.provider

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.kotlinweatherapp.data.db.entity.WeatherLocation
import com.example.kotlinweatherapp.data.provider.PreferencesProvider
import com.example.kotlinweatherapp.internal.LocationPermissionNotGrantedException
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Deferred

// key used to save and retrieve custom location setted in SharedPreferences
const val CUSTOM_LOCATION = "CUSTOM_LOCATION"

// key used to save and retrieve current location of the user from SharedPreferences
const val USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION"

class LocationProviderImpl() : LocationProvider {

    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "London"
    }
}