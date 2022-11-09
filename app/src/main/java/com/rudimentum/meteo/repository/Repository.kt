package com.rudimentum.meteo.repository

import com.rudimentum.meteo.models.Weather
import com.rudimentum.meteo.network.RetrofitInstance
import com.rudimentum.meteo.utils.*
import retrofit2.Response

class WeatherRepository {
    suspend fun getToday() : Response<Weather> {
        return RetrofitInstance.apiService.getTodayWeather(API_KEY, Q, ONE_DAY, AQI, ALERTS)
    }

    suspend fun getWeek() : Response<Weather> {
        return RetrofitInstance.apiService.getWeekForecast(API_KEY, Q, WEEK, AQI, ALERTS)
    }
}