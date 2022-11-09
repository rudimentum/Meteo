package com.rudimentum.meteo.screens.week

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudimentum.meteo.models.Weather
import com.rudimentum.meteo.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class WeekForecastViewModel(private val repository: WeatherRepository): ViewModel() {
    val liveDataCurrent = MutableLiveData<Response<Weather>>()

    fun getWeekForecast() {
        viewModelScope.launch {
            val response = repository.getWeek()
            liveDataCurrent.value = response
        }
    }
}