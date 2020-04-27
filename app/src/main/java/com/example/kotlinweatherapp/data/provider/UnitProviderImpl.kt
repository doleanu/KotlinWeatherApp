package com.example.kotlinweatherapp.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.kotlinweatherapp.internal.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

<<<<<<< Updated upstream
class UnitProviderImpl(context: Context) : UnitProvider {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)
=======
class UnitProviderImpl(context: Context) : PreferencesProvider(context), UnitProvider {
>>>>>>> Stashed changes

    override fun getUnitSystem(): UnitSystem {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }
}