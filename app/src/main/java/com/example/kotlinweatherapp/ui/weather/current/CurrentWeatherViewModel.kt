package com.example.kotlinweatherapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.kotlinweatherapp.data.provider.UnitProvider
import com.example.kotlinweatherapp.data.repository.ForecastRepository
import com.example.kotlinweatherapp.internal.UnitSystem
import com.example.kotlinweatherapp.internal.lazyDeferred
import com.resocoder.forecastmvvm.ui.base.WeatherViewModel

// the view model exposes data for the view
class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }
}
