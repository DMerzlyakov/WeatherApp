package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.api.dto.currentWeather.CurrentWeatherDTO
import com.example.weatherapp.data.api.dto.forecastWeatherDaily.ForecastDT
import retrofit2.Call

// Интерфейс репозитория для работы с данными
// В данном случае работаем только с API
interface Repository {

    // Получить текущую погоду по городу или координатам
    fun getCurrentWeather(city: String): Call<CurrentWeatherDTO>
    fun getCurrentWeather(lat: String, lon: String): Call<CurrentWeatherDTO>

    // Получить прогноз погоды по городу или координатам
    fun getForecastDaily(city: String): Call<ForecastDT>
    fun getForecastDaily(lat: String, lon: String): Call<ForecastDT>

}