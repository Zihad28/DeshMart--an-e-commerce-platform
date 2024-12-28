package com.example.mydeshmart.Products

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydeshmart.R
import com.example.mydeshmart.SelectProduct.SelectMainActivity
import com.example.mydeshmart.SelectProduct.SingleProduct
import com.google.android.material.textfield.TextInputEditText

class ProductMainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: TextInputEditText
    private val viewModel: PostViewModel by viewModels {
        PostViewModel.factory
    }
    private var productList: List<Product> = listOf()
    private lateinit var photoAdapter: PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_main)
        etSearch = findViewById(R.id.etSearch)
        recyclerView = findViewById(R.id.rvProductMain)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        photoAdapter = PhotoAdapter(mutableListOf())
        recyclerView.adapter = photoAdapter

        photoAdapter.setOnItemClickListener { item ->
            val singleProduct = SingleProduct(title= item.title, price = item.price, description = item.description, image = item.image)
            val intent = Intent(this, SelectMainActivity::class.java)
            intent.putExtra("parcelable", singleProduct)
            startActivity(intent)
        }




        viewModel.products.observe(this) { products ->
            productList = products
            photoAdapter.updateData(products)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
        })


        viewModel.getAllProducts()



    }
    private fun filter(query: String) {
        val filteredList = productList.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
        }
        photoAdapter.updateData(filteredList)
    }

}