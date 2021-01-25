package com.example.gstcalculator

import android.os.Bundle
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.widget.Toast
import com.example.gstcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.calcButton.setOnClickListener { view -> handleKeyEvent(view); calculateTax() }
        binding.inputPriceText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    private fun calculateTax() {
        val netPrice = binding.inputPriceText.text.toString().toDoubleOrNull()

        // Null check for empty input
        if (netPrice == null) {
            Toast.makeText(this, "Please enter a valid price !", Toast.LENGTH_SHORT).show()
            return
        }

        val taxPercentage: Double = when (binding.taxOptions.checkedRadioButtonId) {
            binding.r025.id -> 0.25
            binding.r5.id -> 5.0
            binding.r12.id -> 12.0
            binding.r18.id -> 18.0
            binding.r28.id -> 28.0
            else -> 0.0
        }

        val totalTax = (netPrice * taxPercentage) / 100
        val centralGST = totalTax / 2
        val stateGST = totalTax / 2
        val grossPrice = netPrice + centralGST + stateGST

        updateResult(grossPrice.toInt(), totalTax.toInt())
    }

    private fun updateResult(gPrice: Int, tTax: Int) {
        with(binding) {
            grossPrice.text = getString(R.string.grossPriceText, formatCurrency(gPrice))
            centralTax.text = getString(R.string.cgstText, formatCurrency((tTax / 2)))
            stateTax.text = getString(R.string.sgstText, formatCurrency((tTax / 2)))
            totalTax.text = getString(R.string.totalTaxText, formatCurrency(tTax))
        }
    }

    private fun formatCurrency(number: Int): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("INR")
        return format.format(number)
    }

    private fun handleKeyEvent(view: View, keyCode: Int = KeyEvent.KEYCODE_ENTER): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}