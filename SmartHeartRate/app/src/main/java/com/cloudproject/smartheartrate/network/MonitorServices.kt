package com.cloudproject.smartheartrate.network

import com.cloudproject.smartheartrate.model.MonitorResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MonitorServices {
    @POST("monitor/get-rate")
    fun getRate(@Body params:HashMap<String,String>): Call<MonitorResponse>
}