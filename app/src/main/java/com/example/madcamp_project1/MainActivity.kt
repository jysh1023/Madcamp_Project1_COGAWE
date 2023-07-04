package com.example.madcamp_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.madcamp_project1.contact.ContactFragment
import com.example.madcamp_project1.contact.NewContactDialog
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

        val fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.apply {
            add(R.id.recyclerView, ContactFragment())
            add(R.id.newContact, NewContactDialog())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}