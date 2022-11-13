package com.example.weatherapp.domain.model

// Модель данных одного лемента прогноза - прогноз погоды на один день
data class ForecastWeather(
    val temp: Double,
    val description: String,
    val date: String,
    val minTemp: Int,
    val maxTemp: Int,
    val icon: String,
)
