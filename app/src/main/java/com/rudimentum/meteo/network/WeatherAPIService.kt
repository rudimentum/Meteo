package com.rudimentum.meteo.network

import com.rudimentum.meteo.models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("forecast.json?")
    suspend fun getTodayWeather(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: String,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String
    ) : Response<Weather>

    @GET("forecast.json?")
    suspend fun getWeekForecast(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("days") days: String,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String) : Response<Weather>

}