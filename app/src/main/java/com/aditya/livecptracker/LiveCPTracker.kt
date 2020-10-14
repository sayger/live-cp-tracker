package com.aditya.livecptracker

import android.app.Application
import android.content.Context
import android.util.Log

/*
* Implements Global Context Access method without using Dependency Injection
*/
class LiveCPTracker: Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: LiveCPTracker? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
        // Global logger method implements Log.d
        fun logs(msg: String) {
            Log.d("debug -> ", msg)
        }
    }
    override fun onCreate() {
        super.onCreate()
        applicationContext()
    }

}