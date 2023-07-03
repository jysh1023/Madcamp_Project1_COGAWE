package com.example.madcamp_project1.weather

data class Weather(
    val response: Response
) {
    data class Response(
        val header: Header,
        val body: Body
    )
    data class Header(
        val resultCode: Int,
        val resultMsg: String
    )
    data class Body(
        val dataType: String,
        val items: Items
    )
    data class Items(
        val item: List<Item>
    )
    data class Item(
        val category: String,
        val obsrValue: String,
        val fcstValue: String,
        val fcstDate: String,
        val fcstTime: String,
        val nx : Int,
        val ny : Int,
        val baseDate: String,
        val baseTime: String
    )
}
