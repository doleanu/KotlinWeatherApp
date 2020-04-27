package com.example.kotlinweatherapp.data.network.Response


import com.example.kotlinweatherapp.data.db.entity.Request
import com.example.kotlinweatherapp.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(

    val current: Current,
    val location: WeatherLocation,
    val request: Request


)