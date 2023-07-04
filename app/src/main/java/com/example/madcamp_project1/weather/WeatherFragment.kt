package com.example.madcamp_project1.weather

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.madcamp_project1.R
import com.example.madcamp_project1.databinding.FragmentWeatherBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar

class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val weatherInfo: MutableMap<String, String> = mutableMapOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)

        var baseDate: String = "20230704"
        var baseTime: String = "0500"

        // 초단기실황
        // base_time: HH:00, api 제공: +40분
        // mm >= 40 -> HH:00
        // mm < 40 -> HH-1:00
        var calendar = Calendar.getInstance()
        if(calendar.get(Calendar.MINUTE) >= 40) {
            calendar.set(GregorianCalendar.MINUTE, 0)
        }
        else {
            calendar.set(GregorianCalendar.MINUTE, 0)
            calendar.add(GregorianCalendar.HOUR, -1)
        }
        baseDate = calendarToString(calendar, "yyyyMMdd")
        baseTime = calendarToString(calendar, "hhmm")
        RetrofitServiceImpl.service.getUltraSrtNcst(8, 1, baseDate, baseTime).enqueue(
            object : Callback<Weather> {
                override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                    Log.e("myapp", t.toString())
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
                        throw IllegalStateException("api error")
                    }
                    val items = body.component2().component1()
                    for (item in items) {
                        val (category, obsrValue, fcstValue, fcstDate, fcstTime) = item
                        when(category) {
                            "T1H" -> weatherInfo["temperature"] = "%.1f°".format(obsrValue.toFloat())
                            "REH" -> weatherInfo["humidity"] = "습도 ${obsrValue}%"
                            "WSD" -> weatherInfo["wind"] = "바람 ${obsrValue}m/s"
                        }
                    }
                    binding.temperature.text = "${weatherInfo["temperature"]}"
                    binding.wind.text = "${weatherInfo["wind"]}"
                    binding.humidity.text = "${weatherInfo["humidity"]}"
                }
            }
        )

        // 초단기예보
        // base_time HH:30, api 제공 +15분
        // mm >= 45 -> HH:30
        // mm < 45  -> HH-1:30
        calendar = Calendar.getInstance()
        if(calendar.get(Calendar.MINUTE) >= 45) {
            calendar.set(GregorianCalendar.MINUTE, 30)
        }
        else {
            calendar.set(GregorianCalendar.MINUTE, 30)
            calendar.add(GregorianCalendar.HOUR, -1)
        }
        baseDate = calendarToString(calendar, "yyyyMMdd")
        baseTime = calendarToString(calendar, "hhmm")
        RetrofitServiceImpl.service.getUltraSrtFcst(10, 1, baseDate, baseTime).enqueue(
            object : Callback<Weather> {
                override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                    Log.e("myapp", t.toString())
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
                        throw IllegalStateException("api error")
                    }
                    val items = body.component2().component1()
                    for (item in items) {
                        val (category, obsrValue, fcstValue, fcstDate, fcstTime) = item
                        weatherInfo["weatherSummary"] = when(category) {
                            "SKY" -> when(fcstValue) {
                                "1" -> "맑음"
                                "2" -> "구름 많음"
                                "4" -> "흐림"
                                else -> null
                            }
                            "PTY" -> when(fcstValue) {
                                "1" -> "비"
                                "2" -> "비/눈"
                                "3" -> "눈"
                                "5" -> "빗방울"
                                "6" -> "빗방울/눈날림"
                                "7" -> "눈날림"
                                else -> null
                            }
                            else -> null
                        }.orEmpty()
                    }
                    binding.weatherSummary.text = "${weatherInfo["weatherSummary"]}"
                    val src = when(weatherInfo["weatherSummary"]) {
                        in listOf("맑음") -> R.drawable.sky_sunny
                        in listOf("구름 많음") -> R.drawable.sky_many_cloud
                        in listOf("흐림") -> R.drawable.sky_cloudy
                        in listOf("비", "비/눈", "눈", "빗방울", "빗방울/눈날림", "눈날림") -> R.drawable.sky_rainy
                        else -> R.drawable.sky_sunny
                    }
                    Picasso.get()
                        .load(src)
                        .into(binding.weatherIcon)
//                    binding.weatherIcon.setImageResource
                }
            }
        )

        // 단기예보
        // base_time 3x+2:00, api 제공 +10분



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun calendarToString(calendar: Calendar, format: String): String {
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(calendar.time)
    }
}