package com.example.weatherapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// Адаптер для перехода между фрагментами
class ViewsAdapter(fragment: FragmentActivity, private val list: List<Fragment>) :
    FragmentStateAdapter(fragment) {
    // Получаем количество страниц
    override fun getItemCount(): Int {
        return list.size
    }

    // Выбираем выбранный в данный момент фрагмент
    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}