package com.example.mydeshmart.Products

import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProduct(): List<Product>

}