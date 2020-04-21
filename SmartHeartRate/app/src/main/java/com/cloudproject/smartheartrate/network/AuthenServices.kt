package com.cloudproject.smartheartrate.network

import com.cloudproject.smartheartrate.model.AuthenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenServices {
    @POST("/api/user-authen/register")
    fun register(@Body params: HashMap<String, String>): Call<AuthenResponse>

    @POST("/api/user-authen/login")
    fun login(@Body params: HashMap<String, String>): Call<AuthenResponse>
}