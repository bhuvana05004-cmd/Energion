package com.example.energion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ZoneActivity : AppCompatActivity() {

    private lateinit var btnZoneA: Button
    private lateinit var btnZoneB: Button
    private lateinit var btnZoneC: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zone)

        btnZoneA = findViewById(R.id.btnZoneA)
        btnZoneB = findViewById(R.id.btnZoneB)
        btnZoneC = findViewById(R.id.btnZoneC)

        btnZoneA.setOnClickListener {
            openMainActivity("Zone A")
        }

        btnZoneB.setOnClickListener {
            openMainActivity("Zone B")
        }

        btnZoneC.setOnClickListener {
            openMainActivity("Zone C")
        }
    }

    private fun openMainActivity(zoneName: String) {

        val sharedPref = getSharedPreferences("ZoneData", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putString("zone", zoneName)
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
}