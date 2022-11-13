package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherAPI
import com.example.weatherapp.data.api.dto.currentWeather.CurrentWeatherDTO
import com.example.weatherapp.data.api.dto.forecastWeatherDaily.ForecastDT
import com.example.weatherapp.domain.repository.Repository
import retrofit2.Call

// Реализация Репозиторий по интерфейсу
// Обращаемся к интерфесу retrofit для получения данных
class RepositoryImpl constructor(private val apiWeather: WeatherAPI) : Repository {

    override fun getCurrentWeather(city: String): Call<CurrentWeatherDTO> {
        return apiWeather.getCurrentWeather(city)
    }

    override fun getCurrentWeather(lat: String, lon: String): Call<CurrentWeatherDTO> {
        return apiWeather.getCurrentWeather(lat, lon)
    }

    override fun getForecastDaily(city: String): Call<ForecastDT> {
        return apiWeather.getForecastWeather(city)
    }

    override fun getForecastDaily(lat: String, lon: String): Call<ForecastDT> {
        return apiWeather.getForecastWeather(lat, lon)
    }

}