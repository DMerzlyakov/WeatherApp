package com.example.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.domain.model.ForecastWeather
import com.squareup.picasso.Picasso

// Адаптер для RecyclerView с прогнозом погоды
class WeatherListAdapter : ListAdapter<ForecastWeather, ViewHolder>(CustomComparator()) {

    // Подгружаем нашу модель в элемент списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    // Объявляем значения в определённом элементы
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


// Компаратор для сравнения изменний эдементов
class CustomComparator : DiffUtil.ItemCallback<ForecastWeather>() {
    override fun areItemsTheSame(oldItem: ForecastWeather, newItem: ForecastWeather): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ForecastWeather, newItem: ForecastWeather): Boolean {
        return oldItem == newItem
    }

}

// Холдер для изменения данных
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemWeatherBinding.bind(view)
    fun bind(item: ForecastWeather) {
        with(binding) {
            dateText.text = item.date
            averageTemp.text = "Средняя: " + item.temp.toString() + " °C"
            weatherStatus.text = item.description
            minMaxTemp.text = item.maxTemp.toString() + " °C / " + item.minTemp.toString() + " °C"
            Picasso.get().load(item.icon).into(imgView)

        }

    }
}