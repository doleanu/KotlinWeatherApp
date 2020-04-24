package com.example.kotlinweatherapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.kotlinweatherapp.data.provider.UnitProvider
import com.example.kotlinweatherapp.data.repository.ForecastRepository
import com.example.kotlinweatherapp.internal.UnitSystem
import com.example.kotlinweatherapp.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private  val unitSystem = UnitSystem.METRIC  // get from settings later

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
