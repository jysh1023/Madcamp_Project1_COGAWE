package com.example.madcamp_project1.weather

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.FragmentWeatherBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    val adapter = SrtFcstRecyclerViewAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)

        val weatherViewModel =
            ViewModelProvider(activity as FragmentActivity)[WeatherViewModel::class.java]
        weatherViewModel.ncstData.observe(activity as FragmentActivity, Observer { it ->
            binding.ncstTime.text = "${it.ncstTime}"
            binding.temperature.text = "${"%.1f".format(it.ncstTmp)}°"
            binding.wind.text = "바람 ${it.ncstWind}m/s"
            binding.humidity.text = "습도 ${it.ncstHumidity}%"
        })
        weatherViewModel.fcstList.observe(activity as FragmentActivity, Observer { it ->
            binding.weatherSummary.text = it[0].fcstSkySummary
            Picasso.get().load(it[0].fcstSkyDrawableId!!).resize(100, 100).centerCrop()
                .into(binding.weatherIcon)
            adapter.updateData(it as MutableList<FcstData>)
        })

        binding.weatherUltraSrtFcstRecyclerView.adapter = adapter
        binding.weatherUltraSrtFcstRecyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun calendarToString(calendar: Calendar, format: String): String {
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(calendar.time)
    }

    private fun summaryToIconId(summary: String?): Int {
        return when (summary) {
            in listOf("맑음") -> R.drawable.sky_sunny
            in listOf("구름 많음") -> R.drawable.sky_many_cloud
            in listOf("흐림") -> R.drawable.sky_cloudy
            in listOf(
                "비", "비/눈", "눈", "빗방울", "빗방울/눈날림", "눈날림"
            ) -> R.drawable.sky_rainy

            else -> R.drawable.sky_sunny
        }
    }
}