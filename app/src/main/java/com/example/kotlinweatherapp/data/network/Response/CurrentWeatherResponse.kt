package com.example.kotlinweatherapp.data.network.Response

import com.example.kotlinweatherapp.data.db.entity.CurrentWeatherEntry
import com.example.kotlinweatherapp.data.db.entity.Location
import com.example.kotlinweatherapp.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    val request: Request,
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)