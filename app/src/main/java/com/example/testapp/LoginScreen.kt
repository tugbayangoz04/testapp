package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView;

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val emailEditText = findViewById<EditText>(R.id.editEmail)
        val passwordEditText = findViewById<EditText>(R.id.editPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val signupLink = findViewById<TextView>(R.id.textSignupLink)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (email.isEmpty()) {
                emailEditText.error = "Email boş olamaz"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Geçersiz email formatı"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Şifre boş olamaz"
                return@setOnClickListener
            }

            if (password.length < 6) {
                passwordEditText.error = "Şifre en az 6 karakter olmalı"
                return@setOnClickListener
            }

            // Başarılı giriş varsayımı
            Toast.makeText(this, "Giriş başarılı", Toast.LENGTH_SHORT).show()

            // Anasayfaya yönlendirme yapacağımız kısım;
            // startActivity(Intent(this, MainActivity::class.java))
        }

        signupLink.setOnClickListener {
            val intent = Intent(this, SignupScreen::class.java)
            startActivity(intent)
        }
    }
}
