package com.example.kotlinweatherapp.data.network

import com.example.kotlinweatherapp.data.network.Response.CurrentWeatherResponse
import com.example.kotlinweatherapp.data.network.Response.FutureWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "b7395d60ad20417890b222007202704"

interface WeatherApiService {

    // http://api.weatherapi.com/v1/current.json?key=b7395d60ad20417890b222007202704&q=Iasi
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String
    ): Deferred<CurrentWeatherResponse>

    // http://api.weatherapi.com/v1/future.json?key=b7395d60ad20417890b222007202704&days=3&q=Iasi
    @GET("forecast.json")
    fun getFutureWeather(
        @Query("q") location: String,
        @Query("days") days: Int
    ): Deferred<FutureWeatherResponse>

    // same as static fun
    companion object {
        operator fun invoke(
            connectivityInterceptorImpl: ConnectivityInterceptorImpl
        ): WeatherApiService {
            // intercepts the Req and builds the URL
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            // to intercepts every call
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptorImpl) // check if conn to internet
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.weatherapi.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory()) // tells retrofit we're using coroutines
                .addConverterFactory(GsonConverterFactory.create()) // tells Retrofit to parse data using GSON
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}