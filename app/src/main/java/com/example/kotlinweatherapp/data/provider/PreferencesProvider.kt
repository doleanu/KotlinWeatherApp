package com.example.kotlinweatherapp.data.provider

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

abstract class PreferencesProvider(context: Context) {
<<<<<<< Updated upstream

    private val appContext = requireNotNull(context.applicationContext)

    protected val preferences: SharedPreferences
        get() {
            return PreferenceManager.getDefaultSharedPreferences(appContext)
        }
=======
    private val appContext = context.applicationContext

    protected val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
>>>>>>> Stashed changes
}