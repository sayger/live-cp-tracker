package com.aditya.livecptracker.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class APIAccessClass(
    val result: ResultSubClass
) : Parcelable

@Parcelize
data class ResultSubClass(
    val ongoing: List<ContestDetails>,
    val upcoming: List<ContestDetails>,
    val timestamp: String
) : Parcelable

@Parcelize
data class ContestDetails(
    @Json(name = "Name") val name: String,
    @Json(name = "Platform") val platform: String,
    @Json(name = "EndTime") val endTime: String,
    @Json(name = "StartTime") val startTime: String?,
    @Json(name = "Duration") val duration: String?,
    val url: String
) : Parcelable