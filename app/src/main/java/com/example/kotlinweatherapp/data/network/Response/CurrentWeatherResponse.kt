package com.example.kotlinweatherapp.data.network.Response

import com.example.kotlinweatherapp.data.db.entity.CurrentWeatherEntry
import com.example.kotlinweatherapp.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    val location: WeatherLocation,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)