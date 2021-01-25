package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
//import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var diceImage1: ImageView
    lateinit var diceImage2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        diceImage1 = findViewById(R.id.dice_image1)
        diceImage2 = findViewById(R.id.dice_image2)
//        val countUpButton: Button = findViewById(R.id.countup_button)
        val resetButton: Button = findViewById(R.id.reset_button)

        rollButton.setOnClickListener{ rollDice() }
//        countUpButton.setOnClickListener{ incrementCount() }
        resetButton.setOnClickListener{ resetCount() }
    }

    private fun rollDice() {
        //Toast.makeText(this, "Rolling Dice...", Toast.LENGTH_SHORT).show()
        val drawableResource1 = getRandomDrawableResource()
        val drawableResource2 = getRandomDrawableResource()
        diceImage1.setImageResource(drawableResource1)
        diceImage2.setImageResource(drawableResource2)
    }

    private fun getRandomDrawableResource(): Int {
        return when ((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

//    private fun incrementCount() {
//        val resultText: TextView = findViewById(R.id.result_text)
//        val text = resultText.text.toString().toIntOrNull()
//
//        if (text == null) {
//            resultText.text = 1.toString()
//            return
//        }
//
//        if (text == 6) {
//            return
//        }
//
//        resultText.text = (text.plus(1)).toString()
//    }

    private fun resetCount() {
        diceImage1.setImageResource(R.drawable.empty_dice)
        diceImage2.setImageResource(R.drawable.empty_dice)
    }
}