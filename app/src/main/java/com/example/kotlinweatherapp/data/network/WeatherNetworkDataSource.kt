package com.example.kotlinweatherapp.data.network

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.data.network.Response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String
    )
}