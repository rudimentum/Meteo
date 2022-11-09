package com.rudimentum.meteo.screens.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rudimentum.meteo.repository.WeatherRepository

class TodayWeatherViewModelFactory(
    private val repository: WeatherRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodayWeatherViewModel(repository) as T
    }
}