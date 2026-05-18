package com.example.energion

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var signupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        loginBtn = findViewById(R.id.btnLogin)
        signupBtn = findViewById(R.id.btnSignup)

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("UserData", MODE_PRIVATE)

        loginBtn.setOnClickListener {

            val savedEmail = sharedPreferences.getString("email", "")
            val savedPassword = sharedPreferences.getString("password", "")

            val enteredEmail = email.text.toString()
            val enteredPassword = password.text.toString()

            if (enteredEmail == savedEmail &&
                enteredPassword == savedPassword) {

                Toast.makeText(this,
                    "Login Successful",
                    Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, ZoneActivity::class.java))
                finish()

            } else {

                Toast.makeText(this,
                    "Invalid Credentials",
                    Toast.LENGTH_SHORT).show()
            }
        }

        signupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}