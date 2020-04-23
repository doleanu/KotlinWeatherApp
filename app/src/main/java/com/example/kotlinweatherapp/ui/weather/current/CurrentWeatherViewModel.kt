package com.example.kotlinweatherapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.kotlinweatherapp.data.repository.ForecastRepository
import com.example.kotlinweatherapp.internal.UnitSystem
import com.example.kotlinweatherapp.internal.lazyDeerred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private  val unitSystem = UnitSystem.METRIC  // get from settings later

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeerred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
