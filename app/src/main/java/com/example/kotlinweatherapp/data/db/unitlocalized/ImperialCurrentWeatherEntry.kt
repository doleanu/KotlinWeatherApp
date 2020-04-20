package com.resocoder.forecastmvvm.data.db.unitlocalized.current

import androidx.room.ColumnInfo
import com.example.kotlinweatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry


data class ImperialCurrentWeatherEntry(
    override val cloudcover: Double,
    override val feelslike: Double,
    override val humidity: Int,
    override val isDay: String,
    override val observationTime: String,
    override val precip: Double,
    override val pressure: Double,
    override val uvIndex: Int,
    override val visibility: Double,
    override val weatherCode: Int,
    override val weatherDescriptions: String,
    override val weatherIcons: String,
    override val windDegree: Double,
    override val windDir: String,
    override val temperature: Double,
    override val windSpeed: Double
) : UnitSpecificCurrentWeatherEntry