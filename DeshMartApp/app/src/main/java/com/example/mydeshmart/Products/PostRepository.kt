package com.example.mydeshmart.Products

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository {
    private val apiService: ApiService = RetrofitClient.apiService
    suspend fun getAllProducts(): List<Product> {
        return withContext(Dispatchers.IO) {
            apiService.getAllProduct()
        }
    }
}