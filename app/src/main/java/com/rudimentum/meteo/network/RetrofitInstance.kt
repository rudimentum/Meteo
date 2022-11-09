package com.rudimentum.meteo.network

import com.rudimentum.meteo.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: WeatherAPIService by lazy {
        retrofit.create(WeatherAPIService::class.java)
    }
}