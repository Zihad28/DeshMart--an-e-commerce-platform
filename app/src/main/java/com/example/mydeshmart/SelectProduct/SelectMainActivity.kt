package com.example.mydeshmart.SelectProduct

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mydeshmart.R

class SelectMainActivity : AppCompatActivity() {

    private lateinit var ivImage: ImageView
    private lateinit var tvPrice: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnAddToCart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_main)
        ivImage = findViewById(R.id.ivImage)
        tvPrice = findViewById(R.id.tvPrice)
        tvTitle = findViewById(R.id.tvTitle)
        tvDescription = findViewById(R.id.tvDescription)
        btnAddToCart = findViewById(R.id.btnAddToCart)
        if (intent.extras?.containsKey("parcelable") == true) {
            val singleProduct: SingleProduct? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("parcelable", SingleProduct::class.java)
            } else {
                intent.getParcelableExtra<SingleProduct>("parcelable")
            }

            //tvResult.text = "${student?.name}"
            tvTitle.text = "${singleProduct?.title}"
            tvDescription.text = "${singleProduct?.description}"
            val priceToString = singleProduct?.price
            tvPrice.text = "${priceToString.toString()}"+"$"
            Glide.with(this)
                .load(singleProduct?.image) // Use the image URL passed
                .into(ivImage)


        }

        btnAddToCart.setOnClickListener {
            Toast.makeText(this, "Added to Cart", Toast.LENGTH_SHORT).show()
        }


    }

}