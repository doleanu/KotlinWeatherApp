package com.example.kotlinweatherapp

import android.app.Application
<<<<<<< Updated upstream
=======
import android.content.Context
>>>>>>> Stashed changes
import androidx.preference.PreferenceManager
import com.example.kotlinweatherapp.data.db.ForecastDatabase
import com.example.kotlinweatherapp.data.network.*
import com.example.kotlinweatherapp.data.provider.UnitProvider
import com.example.kotlinweatherapp.data.provider.UnitProviderImpl
import com.example.kotlinweatherapp.data.repository.ForecastRepository
import com.example.kotlinweatherapp.data.repository.ForecastRepositoryImpl
import com.example.kotlinweatherapp.ui.weather.current.CurrentWeatherViewModelFactory
<<<<<<< Updated upstream
import com.example.weatherapp.data.provider.LocationProvider
import com.example.weatherapp.data.provider.LocationProviderImpl
=======
import com.example.kotlinweatherapp.ui.weather.future.list.FutureListWeatherViewModelFactory
import com.example.weatherapp.data.provider.LocationProvider
import com.example.weatherapp.data.provider.LocationProviderImpl
import com.google.android.gms.location.LocationServices
>>>>>>> Stashed changes
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
<<<<<<< Updated upstream
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
=======
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate
>>>>>>> Stashed changes
import kotlin.math.sin

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
<<<<<<< Updated upstream
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind() from singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherstackApiService(instance()) }
        bind() from singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind() from singleton { LocationProviderImpl() }
        bind() from singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind() from singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
=======
        bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherstackApiService(instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
        bind() from provider { FutureListWeatherViewModelFactory(instance(), instance()) }



>>>>>>> Stashed changes
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}