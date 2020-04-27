package com.example.kotlinweatherapp.data.repository

import androidx.lifecycle.LiveData
import com.example.kotlinweatherapp.data.db.CurrentWeatherDao
import com.example.kotlinweatherapp.data.db.WeatherLocationDao
import com.example.kotlinweatherapp.data.db.entity.WeatherLocation
import com.example.kotlinweatherapp.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.kotlinweatherapp.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry
import com.example.kotlinweatherapp.data.network.FORECAST_DAYS_COUNT
import com.example.kotlinweatherapp.data.network.Response.CurrentWeatherResponse
import com.example.kotlinweatherapp.data.network.Response.FutureWeatherResponse
import com.example.kotlinweatherapp.data.network.WeatherNetworkDataSource
import com.example.weatherapp.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
<<<<<<< Updated upstream
=======
    private val futureWeatherDao: FutureWeatherDao,
>>>>>>> Stashed changes
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                persistFetchedCurrentWeather(newCurrentWeather)
            }
            downloadedFutureWeather.observeForever { newFutureWeather ->
                persistFetchedFutureWeather(newFutureWeather)
            }
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        initWeatherData()
        return withContext(Dispatchers.IO) {
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

<<<<<<< Updated upstream
=======
    override suspend fun getFutureWeatherList(
        startDate: LocalDate,
        metric: Boolean
    ): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) futureWeatherDao.getSimpleWeatherForecastsMetric(startDate)
            else futureWeatherDao.getSimpleWeatherForecastsImperial(startDate)
        }
        }
>>>>>>> Stashed changes
    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
<<<<<<< Updated upstream
=======
        }
    }
    private fun persistFetchedFutureWeather(fetchedWeather: FutureWeatherResponse) {

        fun deleteOldForecastData() {
            val today = LocalDate.now()
            futureWeatherDao.deleteOldEntries(today)
        }

        GlobalScope.launch(Dispatchers.IO) {
            deleteOldForecastData()
            val futureWeatherList = fetchedWeather.futureWeatherEntries.entries
            futureWeatherDao.insert(futureWeatherList)
            weatherLocationDao.upsert(fetchedWeather.location)
>>>>>>> Stashed changes
        }
    }

    private suspend fun initWeatherData() {
<<<<<<< Updated upstream
        val lastWeatherLocation = weatherLocationDao.getLocation().value

        if(lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
=======
        val lastWeatherLocation = weatherLocationDao.getLocationNonLive()

        if(lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            fetchFutureWeather()
>>>>>>> Stashed changes
            return
        }

        if (isFetchCurrenNeeded(lastWeatherLocation.zoneDateTime))
            fetchCurrentWeather()
    if(isFetchFutureNeeded())
        fetchFutureWeather()

    }

    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            locationProvider.getPreferredLocationString()
        )
<<<<<<< Updated upstream
=======
    }
    private suspend fun fetchFutureWeather() {
        weatherNetworkDataSource.fetchFutureWeather(
            locationProvider.getPreferredLocationString(),

        )
>>>>>>> Stashed changes
    }

    private fun isFetchCurrenNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)


        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
    private fun isFetchFutureNeeded(): Boolean {
        val today = LocalDate.now()
val futureWeatherCount=futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount<FORECAST_DAYS_COUNT
    }
}