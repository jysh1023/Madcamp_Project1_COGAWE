package com.example.madcamp_project1.weather

import com.example.madcamp_project1.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceImpl {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .addCallAdapterFactory(RxJava3?)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}