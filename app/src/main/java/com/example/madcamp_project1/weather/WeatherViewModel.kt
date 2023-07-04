package com.example.madcamp_project1.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcamp_project1.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar

class WeatherViewModel : ViewModel() {
    private val _ncstData = MutableLiveData<NcstData>()
    val ncstData: LiveData<NcstData> get() = _ncstData
    private val _fcstList = MutableLiveData<List<FcstData>>()
    val fcstList: LiveData<List<FcstData>> get() = _fcstList

    init {
        getNcsts()
        getFcsts()
    }

    fun getNcsts() {
        viewModelScope.launch {
            // 초단기실황
            // base_time: HH:00, api 제공: +40분
            // mm >= 40 -> HH:00
            // mm < 40 -> HH-1:00
            val calendar = Calendar.getInstance()
            val requestTime = calendarToString(calendar, "HH:mm")
            if (calendar.get(Calendar.MINUTE) < 40) {
                calendar.add(GregorianCalendar.HOUR_OF_DAY, -1)
            }
            calendar.set(GregorianCalendar.MINUTE, 0)
            val baseDate = calendarToString(calendar, "yyyyMMdd")
            val baseTime = calendarToString(calendar, "HHmm")
            val weatherData = RetrofitServiceImpl.service.getUltraSrtNcst(8, 1, baseDate, baseTime)
            _ncstData.value = weatherToNcst(weatherData, requestTime)
        }
    }

    fun getFcsts() {
        viewModelScope.launch {
            // 초단기예보
            // base_time HH:30, api 제공 +15분
            // mm >= 45 -> HH:30
            // mm < 45  -> HH-1:30
            val calendar = Calendar.getInstance()
            if (calendar.get(Calendar.MINUTE) >= 45) {
                calendar.set(GregorianCalendar.MINUTE, 30)
            } else {
                calendar.set(GregorianCalendar.MINUTE, 30)
                calendar.add(GregorianCalendar.HOUR, -1)
            }
            val baseDate = calendarToString(calendar, "yyyyMMdd")
            val baseTime = calendarToString(calendar, "HHmm")

            // [h, h+1), ... , [h+5, h+6)
            // #(category) = 10
            val weatherData =
                RetrofitServiceImpl.service.getUltraSrtFcst(6 * 10, 1, baseDate, baseTime)
            _fcstList.value = weatherToFcstList(weatherData)
        }
    }

    private fun weatherToNcst(weather: Weather, requestTime: String): NcstData {
        val itemList: List<Weather.Item> = weather.response.body.items.item
        if (weather.response.header.resultCode != 0) {
            throw IllegalStateException("api error")
        }
        val itemMap = itemList.groupBy { it.category }

        val ncstTime = requestTime
        val t1h = itemMap["T1H"]!!.single().obsrValue.toFloat()
        val wsd = itemMap["WSD"]!!.single().obsrValue.toFloat()
        val reh = itemMap["REH"]!!.single().obsrValue.toFloat()

        return NcstData(ncstTime, t1h, wsd, reh)
    }

    private fun weatherToFcstList(weather: Weather): List<FcstData> {
        val itemList: List<Weather.Item> = weather.response.body.items.item
        if (weather.response.header.resultCode != 0) {
            throw IllegalStateException("api error")
        }
        val itemMap = itemList.groupBy { it.category }

        return itemMap["T1H"]!!.zip(itemMap["SKY"]!!).zip(itemMap["PTY"]!!).map { (pair, thr) ->
            val (fst, snd) = pair
            FcstData(
                fst.fcstTime.toInt() / 100,
                when (thr.fcstValue.toInt()) {
                    in listOf(1, 2, 5, 6) -> R.drawable.sky_rainy
                    in listOf(3, 7) -> R.drawable.sky_snow
                    else -> null
                } ?: when (snd.fcstValue.toInt()) {
                    1 -> R.drawable.sky_sunny
                    3 -> R.drawable.sky_many_cloud
                    4 -> R.drawable.sky_cloudy
                    else -> null
                },
                when (thr.fcstValue) {
                    "1" -> "비"
                    "2" -> "비/눈"
                    "3" -> "눈"
                    "5" -> "빗방울"
                    "6" -> "빗방울/눈날림"
                    "7" -> "눈날림"
                    else -> null
                } ?: when (snd.fcstValue) {
                    "1" -> "맑음"
                    "2" -> "구름 많음"
                    "4" -> "흐림"
                    else -> null
                },
                fst.fcstValue.toFloat()
            )
        }
    }

    private fun calendarToString(calendar: Calendar, format: String): String {
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(calendar.time)
    }
}