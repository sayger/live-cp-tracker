package com.aditya.livecptracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.aditya.livecptracker.databinding.ActivityMainBinding
import com.aditya.livecptracker.utils.isNetworkAvailable
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if(!isNetworkAvailable(this)!!) {
            Snackbar.make(binding.containerHead, "No Internet Connection! " +
                    "Please check your network connection.", 5000).show()
        }
    }
}