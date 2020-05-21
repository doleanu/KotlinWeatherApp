package com.example.kotlinweatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinweatherapp.data.db.entity.CurrentWeatherEntry
import com.example.kotlinweatherapp.data.db.entity.WeatherLocation

@Database(
    entities = [CurrentWeatherEntry::class, WeatherLocation::class],
    version = 1
)
@TypeConverters(LocalDateConverter::class)

abstract class ForecastDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun futureWeatherDao(): FutureWeatherDao
    abstract fun weatherLocationDao(): WeatherLocationDao

    // Singleton
    companion object {
        // all the threads have imm access to this prop
        @Volatile private var instance: ForecastDatabase? = null

        // to make sure that no threads are doing the same thing
        private val LOCK = Any()

        // first check if instance is not null, if it is, initialise it
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        // initialise database stored under local storage
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                ForecastDatabase::class.java,
                "forecast.db"
            )
                .build()
    }
}