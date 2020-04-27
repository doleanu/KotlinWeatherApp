package com.example.kotlinweatherapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.kotlinweatherapp.data.provider.UnitProvider
import com.example.kotlinweatherapp.data.repository.ForecastRepository
import com.example.kotlinweatherapp.internal.UnitSystem
import com.example.kotlinweatherapp.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}