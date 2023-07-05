package com.example.madcamp_project1.weather

import com.example.madcamp_project1.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServiceImpl {
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(0, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
//        .addCallAdapterFactory(RxJava3?)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}