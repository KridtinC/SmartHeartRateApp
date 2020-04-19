package com.cloudproject.smartheartrate.network

import com.cloudproject.smartheartrate.model.AddElderResponse
import com.cloudproject.smartheartrate.model.ElderListResponse
import com.cloudproject.smartheartrate.model.MonitorResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountServices {
    @POST("/api/elder-account/get-elderlist")
    fun getElderList(@Body params: HashMap<String, String>): Call<ElderListResponse>

    @POST("/api/elder-account/add-elder")
    fun addElder(@Body params: HashMap<String, String>): Call<AddElderResponse>

}