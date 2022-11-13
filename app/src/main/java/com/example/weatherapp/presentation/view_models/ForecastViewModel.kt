package com.example.weatherapp.presentation.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.api.WeatherAPI
import com.example.weatherapp.data.api.dto.forecastWeatherDaily.ForecastDT
import com.example.weatherapp.data.api.dto.forecastWeatherDaily.getForecast
import com.example.weatherapp.data.repository.RepositoryImpl
import com.example.weatherapp.domain.model.ForecastWeather
import com.example.weatherapp.domain.utils.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel : ViewModel() {

    val liveWeatherForecast = MutableLiveData<List<ForecastWeather>>()

    private val apiWeather = RetrofitHelper.getInstance().create(WeatherAPI::class.java)
    private val repo = RepositoryImpl(apiWeather)

    // Асинхронно обрабатываем отправлленый запрос получения погоды по городу
    // Обрабатываем исключения
    fun updateData(city: String) {
        repo.getForecastDaily(city).enqueue(object : Callback<ForecastDT> {
            override fun onResponse(call: Call<ForecastDT>, response: Response<ForecastDT>) {
                try {
                    liveWeatherForecast.value = (response.body() as ForecastDT).getForecast()
                    Log.d(
                        "API_CONNECTION",
                        (response.body() as ForecastDT).getForecast().toString()
                    )
                } catch (e: NullPointerException) {
                    Log.d("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }

            override fun onFailure(call: Call<ForecastDT>, t: Throwable) {
                Log.d("API_CONNECTION", "Ошибка выполнения запроса прогноза погоды")
            }
        })
    }

    // Асинхронно обрабатываем отправлленый запрос получения прогноза по координатам
    // Обрабатываем исключения
    fun updateData(lat: String, lon: String) {
        repo.getForecastDaily(lat, lon).enqueue(object : Callback<ForecastDT> {
            override fun onResponse(call: Call<ForecastDT>, response: Response<ForecastDT>) {
                try {
                    liveWeatherForecast.value = (response.body() as ForecastDT).getForecast()
                    Log.d(
                        "API_CONNECTION",
                        (response.body() as ForecastDT).getForecast().toString()
                    )
                } catch (e: NullPointerException) {
                    Log.d("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }

            override fun onFailure(call: Call<ForecastDT>, t: Throwable) {
                Log.d("API_CONNECTION", "Ошибка выполнения запроса прогноза погоды")
            }
        })
    }
}