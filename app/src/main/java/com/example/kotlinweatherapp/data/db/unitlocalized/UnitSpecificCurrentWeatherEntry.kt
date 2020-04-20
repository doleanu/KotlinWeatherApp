package com.example.kotlinweatherapp.data.db.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val cloudcover: Double
    val feelslike: Double
    val humidity: Int
    val isDay: String
    val observationTime: String
    val precip: Double
    val pressure: Double
    val temperature: Double
    val uvIndex: Int
    val visibility: Double
    val weatherCode: Int
    val weatherDescriptions: String
    val weatherIcons: String
    val windDegree: Double
    val windDir: String
    val windSpeed: Double
}