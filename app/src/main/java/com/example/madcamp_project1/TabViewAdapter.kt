package com.example.madcamp_project1

import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.madcamp_project1.contact.ContactFragment
import com.example.madcamp_project1.gallery.GalleryFragment
import com.example.madcamp_project1.weather.Weather
import com.example.madcamp_project1.weather.WeatherFragment
import java.lang.RuntimeException

class TabViewAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactFragment()
            1 -> GalleryFragment()
            2 -> WeatherFragment()
            else -> throw RuntimeException("Invalid Position : $position")
        }
    }
}
