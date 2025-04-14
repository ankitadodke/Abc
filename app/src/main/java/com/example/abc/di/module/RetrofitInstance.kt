package com.example.abc.di.module

import com.example.abc.data.remote.YourApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://your.api.url/"


    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // For JSON
            .build()
    }

    val api: YourApiService by lazy {
        retrofit.create(YourApiService::class.java)
    }
}
