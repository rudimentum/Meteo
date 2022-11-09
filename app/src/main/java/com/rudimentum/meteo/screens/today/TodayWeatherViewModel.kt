package com.rudimentum.meteo.screens.today

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodayWeatherViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<String>()
}