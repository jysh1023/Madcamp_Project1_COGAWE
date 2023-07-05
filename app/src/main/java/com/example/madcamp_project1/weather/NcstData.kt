package com.example.madcamp_project1.weather

import com.google.gson.annotations.SerializedName

data class NcstData(
    val ncstTime: String,
    val ncstTmp: Float,
    val ncstWind: Float,
    val ncstHumidity: Float
)
