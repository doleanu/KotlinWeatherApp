package com.example.kotlinweatherapp.data.provider

import com.example.kotlinweatherapp.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}