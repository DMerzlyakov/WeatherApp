package com.example.weatherapp.data.api.dto.forecastWeatherDaily

import com.example.weatherapp.domain.model.ForecastWeather

// Модель данных, полученая по запросу о прогнозе погоды
data class ForecastDT(
    val city_name: String,
    val country_code: String,
    val `data`: List<Data>,
    val lat: String,
    val lon: String,
    val state_code: String,
    val timezone: String
)

// Формируем список с прогнозом по дням
fun ForecastDT.getForecast(): List<ForecastWeather> {
    return data.map { it.getWeather() }
}