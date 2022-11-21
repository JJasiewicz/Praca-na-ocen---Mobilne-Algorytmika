package com.example.losowanie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggle: ToggleButton = findViewById(R.id.toggleButton)
        toggle.setOnCheckedChangeListener{
            _,isChecked -> Toast.makeText(this, if (isChecked) "switch ON " else "switch OFF ", Toast.LENGTH_SHORT).show()
        }


    }
}