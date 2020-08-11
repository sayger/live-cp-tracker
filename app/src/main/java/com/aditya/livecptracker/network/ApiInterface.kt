package com.aditya.livecptracker.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
