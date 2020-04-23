package com.example.kotlinweatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}