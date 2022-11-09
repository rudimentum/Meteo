package com.rudimentum.meteo.screens.today

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudimentum.meteo.models.Weather
import com.rudimentum.meteo.repository.WeatherRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TodayWeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    val liveDataCurrent = MutableLiveData<Response<Weather>>()

    fun getTodayWeather() {
        viewModelScope.launch {
            val response = repository.getToday()
            liveDataCurrent.value = response
        }
    }
}