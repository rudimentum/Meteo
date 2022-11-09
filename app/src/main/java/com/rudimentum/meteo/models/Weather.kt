package com.rudimentum.meteo.models

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)