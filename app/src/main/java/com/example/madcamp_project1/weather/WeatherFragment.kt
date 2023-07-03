package com.example.madcamp_project1.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madcamp_project1.databinding.FragmentWeatherBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)

        val baseDate = "20230703"
        val baseTime = "2200"
        RetrofitServiceImpl.service.getUltraSrtNcst(8, 1, baseDate, baseTime).enqueue(
            object : Callback<Weather> {
                override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                    Log.e("retrofit", t.toString())
                    throw IllegalStateException("fail on getWeather")
                }

                override fun onResponse(
                    call: Call<Weather>,
                    response: Response<Weather>
                ) {
                    val weather: Weather = response.body()!!
                    val (res) = weather
                    val (header, body) = res
                    if (header.component1() != 0) {
                        // todo: api error handling
                        binding.temperature.text = "api error"
                        return
                    }
                    val items = body.component2().component1()
                    for (item in items) {
                        val (category, obsrValue, fcstValue, fcstDate, fcstTime) = item
                        when(category) {
                            "T1H" -> binding.temperature.text = "${obsrValue}°"
                            "REH" -> binding.humidity.text = "습도 ${obsrValue}%"
                            "WSD" -> binding.wind.text = "바람 ${obsrValue}m/s"
                        }
                    }
                }
            }
        )

        RetrofitServiceImpl.service.getUltraSrtFcst(10, 1, baseDate, baseTime).enqueue(
            object : Callback<Weather> {
                override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                    Log.e("retrofit", t.toString())
                    throw IllegalStateException("fail on getWeather")
                }

                override fun onResponse(
                    call: Call<Weather>,
                    response: Response<Weather>
                ) {
                    val weather: Weather = response.body()!!
                    val (res) = weather
                    val (header, body) = res
                    if (header.component1() != 0) {
                        // todo: api error handling
                        binding.temperature.text = "api error"
                        return
                    }
                    val items = body.component2().component1()
                    for (item in items) {
                        val (category, obsrValue, fcstValue, fcstDate, fcstTime) = item
                        when(category) {
                            "SKY" -> when(obsrValue) {
                                "1" -> binding.sky.text = "맑음"
                                "3" -> binding.sky.text = "구름많음"
                                "4" -> binding.sky.text = "흐림"
                            }
                        }
                    }
                }
            }
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}