package com.example.weatherapp.domain.utils

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Расширяем Fragment для проверки установленных разрешений
fun Fragment.isPermissionHave(p: String): Boolean {
    return ContextCompat.checkSelfPermission(
        activity as AppCompatActivity, p
    ) == PackageManager.PERMISSION_GRANTED
}

object RetrofitHelper {

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

