package com.example.kotlinweatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

const val WEATHER_LOCATION_ID = 0

@Entity(tableName = "weather_location")
data class WeatherLocation(
    val name: String,
    val country: String,
    val region: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("timezone_id")
    val timezoneId: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Long
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = WEATHER_LOCATION_ID

    val zoneDateTime: ZonedDateTime
        get() {
            val instant = Instant.ofEpochSecond(localtimeEpoch)
            val zoneId = ZoneId.of(timezoneId)
            return ZonedDateTime.ofInstant(instant, zoneId)
        }
}