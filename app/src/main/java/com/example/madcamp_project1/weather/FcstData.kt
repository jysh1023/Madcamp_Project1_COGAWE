package com.example.madcamp_project1.weather

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FcstData(
    @SerializedName("hour") val fcstHour: Int,
    @SerializedName("sky") val fcstSkyDrawableId: Int?,
    @SerializedName("summary") val fcstSkySummary: String?,
    @SerializedName("temp") val fcstTmp: Float
) : Serializable
