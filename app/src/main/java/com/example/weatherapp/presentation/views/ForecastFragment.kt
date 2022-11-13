package com.example.weatherapp.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentForecastBinding
import com.example.weatherapp.presentation.adapters.WeatherListAdapter
import com.example.weatherapp.presentation.view_models.ForecastViewModel


class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private lateinit var adapter: WeatherListAdapter
    private val modelViewForecast: ForecastViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRecycleView()

    }

    // Объявляем наш RecycleView и присваиваем ему адаптер, обновляем данные, при их получении
    private fun createRecycleView() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherListAdapter()
        recyclerView.adapter = adapter

        modelViewForecast.liveWeatherForecast.observe(viewLifecycleOwner) {
            adapter.submitList(it.subList(1, it.size))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ForecastFragment()
    }
}
