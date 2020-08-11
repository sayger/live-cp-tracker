package com.aditya.livecptracker.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.livecptracker.network.APIAccessClass
import com.aditya.livecptracker.network.ContestDetails
import com.aditya.livecptracker.network.ContestTrackerApi
import com.aditya.livecptracker.network.ResultSubClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingContestsViewModel : ViewModel() {
    private val _apiAccessClassValues = MutableLiveData<APIAccessClass>()
    val apiAccessClassValues : LiveData<APIAccessClass>
        get() = _apiAccessClassValues

    private val _resultSubClassValues = MutableLiveData<ResultSubClass>()
    val resultSubClassValues : LiveData<ResultSubClass>
        get() = _resultSubClassValues

    private val _upcomingContestData = MutableLiveData<List<ContestDetails>>()
    val upcomingContestData : LiveData<List<ContestDetails>>
        get() = _upcomingContestData

    init {
        getContestData()
    }

    private fun getContestData() {
        ContestTrackerApi.retrofitService.getContestData().enqueue(
            object : Callback<APIAccessClass> {
                override fun onFailure(call: Call<APIAccessClass>, t: Throwable) {
                    Log.d("Network Failure : ", t.message.toString())
                }

                override fun onResponse(
                    call: Call<APIAccessClass>,
                    response: Response<APIAccessClass>
                ) {
                    _apiAccessClassValues.value = response.body()
                    _resultSubClassValues.value = _apiAccessClassValues.value?.result
                    _upcomingContestData.value = _resultSubClassValues.value?.upcoming
                    Log.d("response : ", _upcomingContestData.value.toString())
                }

            }
        )
    }
}