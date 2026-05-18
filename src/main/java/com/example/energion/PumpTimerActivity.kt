package com.example.energion

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PumpTimerActivity : AppCompatActivity() {

    private lateinit var tvPumpResult: TextView

    private lateinit var btnRice: Button
    private lateinit var btnWheat: Button
    private lateinit var btnSugarcane: Button
    private lateinit var btnCotton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pump_timer)

        tvPumpResult = findViewById(R.id.tvPumpResult)

        btnRice = findViewById(R.id.btnRice)
        btnWheat = findViewById(R.id.btnWheat)
        btnSugarcane = findViewById(R.id.btnSugarcane)
        btnCotton = findViewById(R.id.btnCotton)

        btnRice.setOnClickListener {
            tvPumpResult.text =
                "Crop : Rice\nRecommended Pump Time : 30 Minutes"
        }

        btnWheat.setOnClickListener {
            tvPumpResult.text =
                "Crop : Wheat\nRecommended Pump Time : 20 Minutes"
        }

        btnSugarcane.setOnClickListener {
            tvPumpResult.text =
                "Crop : Sugarcane\nRecommended Pump Time : 45 Minutes"
        }

        btnCotton.setOnClickListener {
            tvPumpResult.text =
                "Crop : Cotton\nRecommended Pump Time : 25 Minutes"
        }
    }
}