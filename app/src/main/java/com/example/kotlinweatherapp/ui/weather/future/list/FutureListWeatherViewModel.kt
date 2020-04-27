package com.example.kotlinweatherapp.ui.weather.future.list

import androidx.lifecycle.ViewModel
import com.example.kotlinweatherapp.data.provider.UnitProvider
import com.example.kotlinweatherapp.data.repository.ForecastRepository
import com.example.kotlinweatherapp.internal.lazyDeferred
import com.example.kotlinweatherapp.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureListWeatherViewModel(
    forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    class FutureListWeatherViewModel(
        private val forecastRepository: ForecastRepository,
        unitProvider: UnitProvider
    ) : WeatherViewModel(forecastRepository, unitProvider) {

        val weatherEntries by lazyDeferred {
            forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
        }
    }
}
