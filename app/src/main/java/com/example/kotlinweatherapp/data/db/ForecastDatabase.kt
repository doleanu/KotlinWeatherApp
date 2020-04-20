package com.example.kotlinweatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinweatherapp.data.db.entity.CurrentWeatherEntry
import java.security.AccessControlContext

@Database(
    entities = [CurrentWeatherEntry::class],
    version = 1
)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

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
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ForecastDatabase::class.java, "forecast.db")
                .build()
    }
}