package com.example.mydeshmart.Products

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private val BASE_URL = "https://fakestoreapi.com/"

    val apiService: ApiService by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)  // Increase connection timeout
            .writeTimeout(100, TimeUnit.SECONDS)    // Increase write timeout
            .readTimeout(100, TimeUnit.SECONDS)     // Increase read timeout
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // Set the custom client
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}


