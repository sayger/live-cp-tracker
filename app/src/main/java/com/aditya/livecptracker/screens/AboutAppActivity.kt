package com.aditya.livecptracker.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aditya.livecptracker.MainActivity
import com.aditya.livecptracker.R
import com.aditya.livecptracker.databinding.ActivityAboutAppBinding

class AboutAppActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAboutAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_app)
        binding.backBtn.setOnClickListener {
            this.startActivity(Intent(this, MainActivity::class.java))
        }
    }
}