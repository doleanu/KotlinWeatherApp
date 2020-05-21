package com.example.kotlinweatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.data.db.entity.WeatherLocation
import com.example.kotlinweatherapp.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}