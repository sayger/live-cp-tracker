package com.aditya.livecptracker.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.aditya.livecptracker.MainActivity
import com.aditya.livecptracker.R
import com.aditya.livecptracker.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_splash_screen
        )
        Handler().postDelayed({
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }
}