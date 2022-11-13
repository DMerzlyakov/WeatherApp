package com.example.weatherapp.data.api.dto.currentWeather

import com.example.weatherapp.common.Constants.ICON_WEATHER_LINK
import com.example.weatherapp.domain.model.CurrentWeather

// Модель данных, полученая по запросу о текущей погоде
data class CurrentWeatherDTO(
    val count: Int,
    val `data`: List<Data>
)

// Преобразуем в основную модель текущей погоды
fun CurrentWeatherDTO.getWeather(): CurrentWeather {
    return CurrentWeather(
        temp = data[0].app_temp.toInt(),
        city = data[0].city_name,
        description = data[0].weather.description,
        date = data[0].datetime.split(":")[0],
        pressure = data[0].pres.toInt(),
        windSpeed = data[0].wind_spd.toInt(),
        windDirection = data[0].wind_cdir_full,
        humidity = data[0].rh.toInt(),
        fillsLike = data[0].app_temp.toInt(),
        sunrise = data[0].sunrise,
        sunset = data[0].sunset,
        icon = ICON_WEATHER_LINK + data[0].weather.icon.slice(0..2) + "n.png"
    )
}

