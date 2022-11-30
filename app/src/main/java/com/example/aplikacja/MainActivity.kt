package com.example.aplikacja

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bLeft = findViewById<Button>(R.id.wlewo)
        val bRight = findViewById<Button>(R.id.wprawo)
        val res = findViewById<Button>(R.id.submit)
        val images = arrayOf(R.drawable.pies, R.drawable.pies2, R.drawable.pies3)
        val image = findViewById<ImageView>(R.id.imageView)
        bLeft.setOnClickListener() {
            when (image.drawable.constantState) {
                resources.getDrawable(images[0]).constantState -> {
                    image.setImageResource(images[2])
                }
                resources.getDrawable(images[1]).constantState -> {
                    image.setImageResource(images[0])
                }
                resources.getDrawable(images[2]).constantState -> {
                    image.setImageResource(images[1])
                }
            }
        }
        bRight.setOnClickListener() {
            when (image.drawable.constantState) {
                resources.getDrawable(images[0]).constantState -> {
                    image.setImageResource(images[1])
                }
                resources.getDrawable(images[1]).constantState -> {
                    image.setImageResource(images[2])
                }
                resources.getDrawable(images[2]).constantState -> {
                    image.setImageResource(images[0])
                }
            }
        }
        res.setOnClickListener() {
            val text = findViewById<EditText>(R.id.silnia)
            val number = text.text.toString().toInt()
            val result = number*1*2*3
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Result")
            builder.setMessage("Silnia z liczby $number wynosi: $result")
            builder.setPositiveButton("OK", null)
            builder.show()
        }

    }
}