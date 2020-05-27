package com.example.kotlinweatherapp

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.kotlinweatherapp.data.db.ForecastDatabase
import com.example.kotlinweatherapp.data.network.*
import com.example.kotlinweatherapp.data.provider.UnitProviderImpl
import com.example.kotlinweatherapp.data.repository.ForecastRepositoryImpl
import com.example.kotlinweatherapp.ui.weather.current.CurrentWeatherViewModelFactory
import com.example.kotlinweatherapp.ui.weather.future.detail.FutureDetailWeatherViewModelFactory
import com.example.kotlinweatherapp.ui.weather.future.list.FutureListWeatherViewModelFactory
import com.example.weatherapp.data.provider.LocationProviderImpl
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate

// was set in android manifest to use this class when app is launched
class ForecastApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {

        // to add what android needs and use instance()
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind() from singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiService(instance()) }
        bind() from singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind() from singleton { LocationProviderImpl(instance(), instance()) }
        bind() from singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance(), instance()) }
        bind() from singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
        bind() from provider { FutureListWeatherViewModelFactory(instance(), instance()) }
        bind() from factory { detailDate: LocalDate -> FutureDetailWeatherViewModelFactory(detailDate, instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        // init datetime  lib
        AndroidThreeTen.init(this)

        // if the used has not set any preferences, use the default ones
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}