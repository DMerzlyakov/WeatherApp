package com.example.weatherapp.data.api.dto

data class ForecastOverWeekDTO(
    val city_name: String,
    val country_code: String,
    val `data`: List<DataDTO>,
    val lat: String,
    val lon: String,
    val state_code: String,
    val timezone: String
)