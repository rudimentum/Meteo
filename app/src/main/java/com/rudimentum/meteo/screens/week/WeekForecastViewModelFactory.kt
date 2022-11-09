package com.rudimentum.meteo.screens.week

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rudimentum.meteo.repository.WeatherRepository

class WeekForecastViewModelFactory(
    private val repository: WeatherRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeekForecastViewModel(repository) as T
    }
}