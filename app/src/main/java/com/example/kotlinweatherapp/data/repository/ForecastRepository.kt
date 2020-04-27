package com.example.kotlinweatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.data.db.entity.CurrentWeatherEntry
import com.example.kotlinweatherapp.data.db.entity.WeatherLocation
import com.example.kotlinweatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.kotlinweatherapp.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
<<<<<<< Updated upstream
=======

    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>

    //suspend fun getFutureWeatherByDate(date: LocalDate, metric: Boolean): LiveData<out UnitSpecificDetailFutureWeatherEntry>

>>>>>>> Stashed changes
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}