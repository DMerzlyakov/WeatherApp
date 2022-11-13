package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.dto.currentWeather.CurrentWeatherDTO
import com.example.weatherapp.data.api.dto.forecastWeatherDaily.ForecastDT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Интерфейс для реализации запросов по адресу
interface WeatherAPI {

    @GET("current?key=fd5991c796194742b5f0a663c468dac2&lang=ru")
    fun getCurrentWeather(@Query("city") city: String): Call<CurrentWeatherDTO>

    @GET("current?key=fd5991c796194742b5f0a663c468dac2&lang=ru")
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Call<CurrentWeatherDTO>

    @GET("forecast/daily?key=fd5991c796194742b5f0a663c468dac2&lang=ru&days=8")
    fun getForecastWeather(@Query("city") city: String): Call<ForecastDT>

    @GET("forecast/daily?key=fd5991c796194742b5f0a663c468dac2&lang=ru&days=8")
    fun getForecastWeather(@Query("lat") lat: String, @Query("lon") lon: String): Call<ForecastDT>
}


