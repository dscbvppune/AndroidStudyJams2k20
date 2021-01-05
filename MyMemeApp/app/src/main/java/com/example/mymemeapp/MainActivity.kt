package com.example.mymemeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val previousButton: Button = findViewById(R.id.prevButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        previousButton.setOnClickListener {
            previousTemplate()
        }
        nextButton.setOnClickListener {
            nextTemplate()
        }
    }

    private fun previousTemplate() {
        val toast: Toast = Toast.makeText(this, "Showing Previous Meme", Toast.LENGTH_SHORT)
        toast.show()

        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setImageResource(R.drawable.meme1)
    }

    private fun nextTemplate() {
        val toast: Toast = Toast.makeText(this, "Showing Next Meme", Toast.LENGTH_SHORT)
        toast.show()

        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setImageResource(R.drawable.meme2)
    }
}