package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Abhishek Dubey")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
    }

    fun addNickname(view: View) {
        val nicknameTextView = binding.nicknameText
        val nicknameTextInputLayout = binding.nicknameEditLayout

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
        }

        // Changing visibilities
        nicknameTextInputLayout.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE

        // Hiding keyboard
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun updateNickname(view: View) {
        val editText = binding.nicknameEdit
        val nicknameTextInputLayout = binding.nicknameEditLayout

        // Changing visibilities
        binding.nicknameText.visibility = View.GONE
        nicknameTextInputLayout.visibility = View.VISIBLE

        // Requesting keyboard focus
        editText.requestFocus()

        // Showing keyboard
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(editText, 0)
    }
}