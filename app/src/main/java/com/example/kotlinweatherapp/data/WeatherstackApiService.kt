package com.example.kotlinweatherapp.data

import com.example.kotlinweatherapp.data.Response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "218812410024cfdb3a72ac27d80835c3"

// http://api.weatherstack.com/current?access_key=218812410024cfdb3a72ac27d80835c3&query=New%20York&language=en

interface WeatherstackApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("query") location: String
//        @Query("language") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(): WeatherstackApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherstackApiService::class.java)
        }
    }
}