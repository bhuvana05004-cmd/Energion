package com.example.energion

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        name = findViewById(R.id.etName)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        signupBtn = findViewById(R.id.btnSignup)

        val sharedPreferences: SharedPreferences =
            getSharedPreferences("UserData", MODE_PRIVATE)

        signupBtn.setOnClickListener {

            val editor = sharedPreferences.edit()

            editor.putString("email", email.text.toString())
            editor.putString("password", password.text.toString())

            editor.apply()

            Toast.makeText(this,
                "Account Created Successfully",
                Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}