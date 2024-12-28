package com.example.mydeshmart.SignInUp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mydeshmart.Products.ProductMainActivity
import com.example.mydeshmart.R
import com.example.mydeshmart.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvSignUp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        binding.tvSignUpp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if(it.isSuccessful) {
                        val intent = Intent(this, ProductMainActivity::class.java)
                        startActivity(intent)
                    }else {
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }else {

                Toast.makeText(this,"password is not matching", Toast.LENGTH_LONG).show()
            }

        }




    }
}