package com.example.madcamp_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.madcamp_project1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayoutMediator =
            TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Contact"
                    1 -> tab.text = "Gallery"
                    2 -> tab.text = "Weather"
                }
            }

        binding.viewPager.adapter = TabViewAdapter(this)
        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}