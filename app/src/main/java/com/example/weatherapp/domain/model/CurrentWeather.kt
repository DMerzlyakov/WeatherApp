package com.example.weatherapp.domain.model

// Модель данные о текущей погоде
data class CurrentWeather(
    val temp: Int,
    val city: String,
    val description: String,
    val date: String,
    val icon: String,
    val fillsLike: Int,
    val sunrise: String,
    val sunset: String,
    val windSpeed: Int,
    val windDirection: String,
    val pressure: Int,
    val humidity: Int
)
