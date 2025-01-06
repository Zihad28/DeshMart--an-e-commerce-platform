package com.example.mydeshmart.Products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel (private val repository: PostRepository): ViewModel(){
    private var _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun getAllProducts() {
        viewModelScope.launch {
            try {
                val allPost = repository.getAllProducts()
                _products.postValue(allPost)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PostViewModel(PostRepository()) as T
            }
        }
    }

}