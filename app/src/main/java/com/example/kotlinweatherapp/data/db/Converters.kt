package com.example.kotlinweatherapp.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson


object Converters {

    @TypeConverter
    @JvmStatic
    fun stringToStringList(data: String?): List<String>? {
        val list = data?.split(",")

        return list?.filter { !it.isEmpty() }
    }

    @TypeConverter
    @JvmStatic

    fun stringListToString(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}