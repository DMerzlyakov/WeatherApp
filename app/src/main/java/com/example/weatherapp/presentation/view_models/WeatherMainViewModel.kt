package com.example.weatherapp.presentation.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.api.WeatherAPI
import com.example.weatherapp.data.api.dto.currentWeather.CurrentWeatherDTO
import com.example.weatherapp.data.api.dto.currentWeather.getWeather
import com.example.weatherapp.data.repository.RepositoryImpl
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.utils.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherMainViewModel : ViewModel() {

    val liveWeatherCurrent = MutableLiveData<CurrentWeather>()
    val liveError = MutableLiveData<String>()

    // Инициальзируем репозиторий для работы с API через retrofit
    private val apiWeather = RetrofitHelper.getInstance().create(WeatherAPI::class.java)
    private val repo = RepositoryImpl(apiWeather)

    // Асинхронно обрабатываем отправлленый запрос получения погоды по городу
    // Обрабатываем исключения
    fun updateData(city: String) {
        repo.getCurrentWeather(city).enqueue(object : Callback<CurrentWeatherDTO> {
            override fun onResponse(
                call: Call<CurrentWeatherDTO>,
                response: Response<CurrentWeatherDTO>
            ) {
                try {
                    liveWeatherCurrent.value = (response.body() as CurrentWeatherDTO).getWeather()
                    Log.d(
                        "API_CONNECTION",
                        (response.body() as CurrentWeatherDTO).getWeather().toString()
                    )
                } catch (e: NullPointerException) {
                    liveError.value = "Сервер временно не отвечает. Попробуйте позже"
                    Log.d("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }

            override fun onFailure(call: Call<CurrentWeatherDTO>, t: Throwable) {
                liveError.value = "Проверьте ваше интернет соединение"
                Log.d("API_CONNECTION", "Ошибка выполнения запроса текущей погоды: $t")
            }

        })
    }

    // Асинхронно обрабатываем отправлленый запрос получения погоды по координатам
    // Обрабатываем исключения
    fun updateData(lat: String, lon: String) {
        repo.getCurrentWeather(lat, lon).enqueue(object : Callback<CurrentWeatherDTO> {
            override fun onResponse(
                call: Call<CurrentWeatherDTO>,
                response: Response<CurrentWeatherDTO>
            ) {
                try {
                    liveWeatherCurrent.value = (response.body() as CurrentWeatherDTO).getWeather()
                    Log.d(
                        "API_CONNECTION",
                        (response.body() as CurrentWeatherDTO).getWeather().toString()
                    )
                } catch (e: NullPointerException) {
                    liveError.value = "Сервер временно не отвечает. Попробуйте позже"
                    Log.d("API_CONNECTION", "Сервер не предоставил необходимую информацию")
                }
            }

            override fun onFailure(call: Call<CurrentWeatherDTO>, t: Throwable) {
                liveError.value = "Проверьте ваше интернет соединение"
                Log.d("API_CONNECTION", "Ошибка выполнения запроса текущей погоды: $t")
            }

        })
    }

}