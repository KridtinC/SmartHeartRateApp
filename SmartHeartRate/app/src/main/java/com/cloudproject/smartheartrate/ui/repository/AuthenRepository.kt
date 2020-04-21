package com.cloudproject.smartheartrate.ui.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudproject.smartheartrate.model.AddElderResponse
import com.cloudproject.smartheartrate.model.AuthenResponse
import com.cloudproject.smartheartrate.network.AuthenServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val hostname = "13.251.26.36"

class AuthenRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://$hostname:5001/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun register(email: String, password: String, firstName: String, lastName: String): MutableLiveData<String> {
        val registerSuccess: MutableLiveData<String> = MutableLiveData<String>()
        val body: HashMap<String, String> = HashMap()
        body["email"] = email
        body["password"] = password
        body["firstName"] = firstName
        body["lastName"] = lastName

        var req: Call<AuthenResponse> = retrofit.create(AuthenServices::class.java).register(body)
        req.enqueue(object : Callback<AuthenResponse?> {
            override fun onResponse(call: Call<AuthenResponse?>, response: Response<AuthenResponse?>) {
                if (response.isSuccessful) {
                    registerSuccess.value = response.body()?.result
                } else {
                    Log.e("AuthenRepo", "onResponse: " + response.errorBody())
                    registerSuccess.value = response.body()?.error
                }
            }

            override fun onFailure(call: Call<AuthenResponse?>, t: Throwable) {
                registerSuccess.value = t.message
                Log.e("AuthenRepo", "onFailure: " + t.message)
            }
        })
        return registerSuccess
    }

    fun login(email: String, password: String): MutableLiveData<String> {
        val loginSuccess: MutableLiveData<String> = MutableLiveData<String>()
        val body: HashMap<String, String> = HashMap()
        body["email"] = email
        body["password"] = password

        var req: Call<AuthenResponse> = retrofit.create(AuthenServices::class.java).login(body)
        req.enqueue(object : Callback<AuthenResponse?> {
            override fun onResponse(call: Call<AuthenResponse?>, response: Response<AuthenResponse?>) {
                if (response.isSuccessful) {
                    loginSuccess.value = response.body()?.result

                } else {
                    loginSuccess.value = response.body()?.error
                    Log.e("AuthenRepo", "onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<AuthenResponse?>, t: Throwable) {
                loginSuccess.value = t.message
                Log.e("AuthenRepo", "onFailure: " + t.message)
            }
        })
        return loginSuccess
    }
}