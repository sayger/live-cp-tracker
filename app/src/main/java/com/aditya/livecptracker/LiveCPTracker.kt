package com.aditya.livecptracker

import android.app.Application
import android.content.Context
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
    }
    override fun onCreate() {
        super.onCreate()
        applicationContext()
    }
}