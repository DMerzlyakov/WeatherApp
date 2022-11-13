package com.example.weatherapp.domain.repository

interface Repository {
    // Описываем необходимые методы репозитория

    fun getCurrentWeatherByCity() {}

    fun getForecastDailyByCityOverWeek() {}

    fun getHourlyForecastByCityOverOneDay() {}
}