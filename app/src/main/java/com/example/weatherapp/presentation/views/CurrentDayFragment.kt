package com.example.weatherapp.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentCurrentDayBinding
import com.example.weatherapp.presentation.view_models.WeatherMainViewModel
import com.squareup.picasso.Picasso


class CurrentDayFragment : Fragment() {

    private lateinit var binding: FragmentCurrentDayBinding
    private val modelViewMain: WeatherMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateCurrentWeather()
    }

    // Подписываемся на изменения и обновляем данные при их изменении
    private fun updateCurrentWeather() = with(binding) {
        modelViewMain.liveWeatherCurrent.observe(viewLifecycleOwner) {
            feelLikeText.text = "Ощущается как ${it.fillsLike}°C"
            sunriseText.text = "Рассвет: ${it.sunrise}"
            sunsetText.text = "Закат: ${it.sunset}"
            windSpeedText.text = "Скорость ветра: ${it.windSpeed} м/c"
            windDerectionText.text = "Направление ветра: ${it.windDirection}"
            humidityText.text = "Влажность: ${it.humidity}%"
            pressureText.text = "Давление: ${it.pressure} мБар"
            Picasso.get().load(it.icon).into(weatherImage)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CurrentDayFragment()
    }
}