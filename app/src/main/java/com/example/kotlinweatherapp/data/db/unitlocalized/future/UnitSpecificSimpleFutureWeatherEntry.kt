package com.example.kotlinweatherapp.data.db.unitlocalized.future

import org.threeten.bp.LocalDate

interface UnitSpecificSimpleFutureWeatherEntry {
    val date: LocalDate
    val maxTemperature: Double
    val minTemperature: Double
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val maxWindSpeed: Double
    val totalPrecipitation: Double
    val avgVisibilityDistance: Double
    val uv: Double
}