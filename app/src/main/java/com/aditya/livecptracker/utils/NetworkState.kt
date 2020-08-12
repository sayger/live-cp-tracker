package com.aditya.livecptracker.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isNetworkAvailable(ctx: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if(activeNetwork != null && activeNetwork.isConnected) isConnected = true
    return isConnected
}