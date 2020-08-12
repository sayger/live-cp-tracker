package com.aditya.livecptracker.network

import android.content.Context
import com.aditya.livecptracker.LiveCPTracker
import com.aditya.livecptracker.utils.isNetworkAvailable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://contesttrackerapi.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient(LiveCPTracker.applicationContext()))
    .build()

interface ContestApiService {
    @GET("/")
    fun getContestData() : Call<APIAccessClass>
}

object ContestTrackerApi {
    val retrofitService: ContestApiService by lazy {
        retrofit.create(ContestApiService::class.java)
    }
}

fun okHttpClient(ctx: Context): OkHttpClient {
    val cacheSize = (5 * 1024 * 1024).toLong()
    val cache = Cache(ctx.cacheDir, cacheSize)
    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor{
            var request = it.request()
            request = if(isNetworkAvailable(ctx)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only if cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            it.proceed(request)
        }
        .build()
}
