package com.example.kotlinweatherapp.data.network

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.data.network.Response.CurrentWeatherResponse
import com.example.kotlinweatherapp.data.network.Response.FutureWeatherResponse


interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String
    )

    val downloadedFutureWeather:LiveData<FutureWeatherResponse>

    suspend fun fetchFutureWeather(
        location:String
    )
}