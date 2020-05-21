package com.example.kotlinweatherapp.ui.weather.future.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinweatherapp.data.provider.UnitProvider
import com.example.kotlinweatherapp.data.repository.ForecastRepository

class FutureWeatherListViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureWeatherListViewModelFactory(forecastRepository, unitProvider) as T
    }
}