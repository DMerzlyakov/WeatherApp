package com.example.weatherapp.data.api

import com.example.weatherapp.common.Constants.API_KEY
import com.example.weatherapp.common.Constants.CURRENT_WEATHER_LINK
import com.example.weatherapp.data.api.dto.currentWeather.CurrentWeatherDTO
import com.example.weatherapp.data.api.dto.forecastWeatherDaily.ForecastDT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Интерфейс для реализации запросов по адресу
interface WeatherAPI {

    @GET("${CURRENT_WEATHER_LINK}key=$API_KEY&lang=ru")
    fun getCurrentWeather(@Query("city") city: String): Call<CurrentWeatherDTO>

    @GET("${CURRENT_WEATHER_LINK}key=$API_KEY&lang=ru")
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Call<CurrentWeatherDTO>

    @GET("forecast/daily?key=$API_KEY&lang=ru&days=8")
    fun getForecastWeather(@Query("city") city: String): Call<ForecastDT>

    @GET("forecast/daily?key=$API_KEY&lang=ru&days=8")
    fun getForecastWeather(@Query("lat") lat: String, @Query("lon") lon: String): Call<ForecastDT>
}


