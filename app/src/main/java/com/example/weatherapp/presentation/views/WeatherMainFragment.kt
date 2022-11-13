package com.example.weatherapp.presentation.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherapp.databinding.WeatherMainFragmentBinding
import com.example.weatherapp.domain.utils.isPermissionHave
import com.example.weatherapp.presentation.adapters.ViewsAdapter
import com.example.weatherapp.presentation.dialogs.DialogUtil
import com.example.weatherapp.presentation.view_models.ForecastViewModel
import com.example.weatherapp.presentation.view_models.WeatherMainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.tabs.TabLayoutMediator


class WeatherMainFragment : Fragment() {
    private lateinit var binding: WeatherMainFragmentBinding
    private lateinit var launcher: ActivityResultLauncher<String>
    private lateinit var locationClient: FusedLocationProviderClient

    private val fragmentList = listOf(
        CurrentDayFragment.newInstance(),
        ForecastFragment.newInstance()
    )

    private val fragmentNameList = listOf("Текущая погода", "Прогноз погоды")
    private val modelViewMain: WeatherMainViewModel by activityViewModels()
    private val modelViewForecast: ForecastViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherMainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем Клиент для получения местоположения
        locationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        with(binding) {

            // Обрабатываем нажатия на кнопку обновления данных
            refreshDataButton.setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                getLocation()

            }

            // Обрабатываем нажатия на кнопку получения прогноза по поиске
            findButton.setOnClickListener {
                it.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                DialogUtil.searchByName(requireContext(), object : DialogUtil.Listener {
                    override fun getData(name: String) {
                        updateData(name)
                    }
                })
            }
        }

        // Адаптер для смены фрагментов внутри vPager с поммощью tabLayout
        binding.viewPager.adapter = ViewsAdapter(activity as FragmentActivity, fragmentList)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { value, position ->
            value.text = fragmentNameList[position]
        }.attach()

        checkGpsPermission()
        getLocation()
        updateCurrentWeather()

    }

    // Получаем местоположение по GPS и обновляем данные
    private fun getLocation() {
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            binding.weatherText.text = "Местоположение недоступно"
            return
        }
        locationClient
            .getCurrentLocation(PRIORITY_HIGH_ACCURACY, ct.token)
            .addOnCompleteListener {
                updateData(it.result.latitude.toString(), it.result.longitude.toString())
                Log.d("LOCATION_GET", "${it.result.latitude}   ${it.result.longitude}")
            }
    }

    // Проверяем разрешение на использование данных о местоположении
    private fun checkGpsPermission() {
        if (!isPermissionHave(Manifest.permission.ACCESS_FINE_LOCATION)) {
            launcher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { getLocation() }
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }
    }

    // Обноваляем данные через название города
    private fun updateData(city: String) {
        modelViewMain.updateData(city)
        modelViewForecast.updateData(city)
    }

    // ОБновляем данные через координаты
    private fun updateData(lat: String, lon: String) {
        modelViewMain.updateData(lat, lon)
        modelViewForecast.updateData(lat, lon)
    }

    // Привязываемся к обсерверу и заменяем данные, при их появлении
    private fun updateCurrentWeather() = with(binding) {
        modelViewMain.liveWeatherCurrent.observe(viewLifecycleOwner) {
            if (progressBar.isVisible) {
                progressBar.visibility = ProgressBar.INVISIBLE
                viewPager.visibility = ViewPager2.VISIBLE
            }
            cityText.text = it.city
            weatherText.text = it.description
            tempCurrentText.text = it.temp.toString() + "°C"
            dataText.text = it.date
        }

        // Обрабатываем ошибки
        modelViewMain.liveError.observe(viewLifecycleOwner) {
            DialogUtil.errorDialog(requireContext(), it)
            if (progressBar.isVisible) {
                weatherText.text = it
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherMainFragment()
    }

}