package com.example.madcamp_project1.weather

import com.example.madcamp_project1.util.Constants.Companion.API_KEY
import com.example.madcamp_project1.util.Constants.Companion.KAIST_NX
import com.example.madcamp_project1.util.Constants.Companion.KAIST_NY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("getUltraSrtNcst?serviceKey=$API_KEY&nx=$KAIST_NX&ny=$KAIST_NY&dataType=JSON")
    suspend fun getUltraSrtNcst(
        @Query("numOfRows") numOfRows : Int,
        @Query("pageNo") pageNo : Int,
        @Query("base_date") baseDate : String,
        @Query("base_time") baseTime : String,
    ) : Weather

    @GET("getUltraSrtFcst?serviceKey=$API_KEY&nx=$KAIST_NX&ny=$KAIST_NY&dataType=JSON")
    suspend fun getUltraSrtFcst(
        @Query("numOfRows") numOfRows : Int,
        @Query("pageNo") pageNo : Int,
        @Query("base_date") baseDate : String,
        @Query("base_time") baseTime : String
    ) : Weather

    @GET("getVilageFcst?serviceKey=$API_KEY&nx=$KAIST_NX&ny=$KAIST_NY&dataType=JSON")
    suspend fun getVilageFcst(
        @Query("numOfRows") numOfRows : Int,
        @Query("pageNo") pageNo : Int,
        @Query("base_date") baseDate : String,
        @Query("base_time") baseTime : String
    ) : Call<Weather>
}