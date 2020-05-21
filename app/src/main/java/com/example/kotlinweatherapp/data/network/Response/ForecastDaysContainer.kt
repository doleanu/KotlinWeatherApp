package com.example.kotlinweatherapp.data.network.Response


import com.example.kotlinweatherapp.data.db.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDaysContainer(
    @SerializedName("forecastDay")
    val entries: List<FutureWeatherEntry>
)