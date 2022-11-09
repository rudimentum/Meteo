package com.rudimentum.meteo.screens.week

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeekForecastViewModel: ViewModel() {
    val liveDataList = MutableLiveData<List<String>>()
}