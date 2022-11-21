package com.example.yes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            findViewById<ImageView>(R.id.obrazek).setImageResource(R.drawable.jablko)
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            findViewById<ImageView>(R.id.obrazek).setImageResource(R.drawable.truskawka)
        }

        findViewById<Button>(R.id.button3).setOnClickListener {

            findViewById<ImageView>(R.id.obrazek).setImageResource(R.drawable.kokos)
        }

    }
}