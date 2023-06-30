package com.example.madcamp_project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.madcamp_project1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    private var _bind: ActivityMainBinding? = null
    private val bind get() = _bind!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        _bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val tabLayoutMediator = TabLayoutMediator(bind.tabs, bind.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Contact"
                1 -> tab.text = "Gallery"
                2 -> tab.text = "???"
            }
        }

        bind.viewPager.adapter = MyAdapter(this)
        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _bind = null
    }

    class MyAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> ContactFragment()
                1 -> GalleryFragment()
                2 -> GalleryFragment()  // todo: Tab3Fragment
                else -> throw RuntimeException("Invalid Position : $position")
            }
        }
    }
}