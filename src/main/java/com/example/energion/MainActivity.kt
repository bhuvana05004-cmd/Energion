package com.example.energion

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var tvZone: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvLastSeen: TextView

    private lateinit var btnOn: Button
    private lateinit var btnOff: Button
    private lateinit var btnPumpTimer: Button
    private lateinit var btnLogout: Button

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvZone = findViewById(R.id.tvZone)
        tvStatus = findViewById(R.id.tvStatus)
        tvLastSeen = findViewById(R.id.tvLastSeen)

        btnOn = findViewById(R.id.btnOn)
        btnOff = findViewById(R.id.btnOff)
        btnPumpTimer = findViewById(R.id.btnPumpTimer)
        btnLogout = findViewById(R.id.btnLogout)

        // Firebase reference
        database = FirebaseDatabase
            .getInstance("https://energion-2c8cc-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .reference
            .child("PowerStatus")

        // Load zone
        val zonePref = getSharedPreferences("ZoneData", MODE_PRIVATE)
        val selectedZone = zonePref.getString("zone", "No Zone")
        tvZone.text = "Selected Zone : $selectedZone"

        // 🔥 REAL-TIME LISTENER (THIS IS THE MAIN FIX)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val status = snapshot.child("status").getValue(String::class.java) ?: "OFF"
                val timestamp = snapshot.child("timestamp").getValue(Long::class.java) ?: 0L

                val time = if (timestamp != 0L) {
                    SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(timestamp))
                } else {
                    "No Updates Yet"
                }

                tvLastSeen.text = "Last Seen : $time"

                if (status == "ON") {
                    tvStatus.text = "POWER STATUS : ON"
                    tvStatus.setTextColor(Color.GREEN)
                } else {
                    tvStatus.text = "POWER STATUS : OFF"
                    tvStatus.setTextColor(Color.RED)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        // ON button → Firebase update
        btnOn.setOnClickListener {
            val currentTime = System.currentTimeMillis()

            val data = mapOf(
                "status" to "ON",
                "timestamp" to currentTime
            )

            database.setValue(data)
        }

        // OFF button → Firebase update
        btnOff.setOnClickListener {
            val currentTime = System.currentTimeMillis()

            val data = mapOf(
                "status" to "OFF",
                "timestamp" to currentTime
            )

            database.setValue(data)
        }

        // Navigation
        btnPumpTimer.setOnClickListener {
            startActivity(Intent(this, PumpTimerActivity::class.java))
        }

        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}