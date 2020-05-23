package com.example.kotlinweatherapp.ui.weather.future.detail

import com.example.kotlinweatherapp.data.provider.UnitProvider
import com.example.kotlinweatherapp.data.repository.ForecastRepository
import com.example.kotlinweatherapp.internal.lazyDeferred
import com.resocoder.forecastmvvm.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureDetailWeatherViewModel(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider){

    val weather by lazyDeferred {
        forecastRepository.getFutureWeatherByDate(detailDate, super.isMetricUnit)
    }
}
