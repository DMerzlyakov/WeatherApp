package com.example.weatherapp.data.api

import retrofit2.http.GET

interface WeatherAPI {

    @GET()
    getForecastOverWeek

}