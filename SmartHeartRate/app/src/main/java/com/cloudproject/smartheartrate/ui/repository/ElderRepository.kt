package com.cloudproject.smartheartrate.ui.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudproject.smartheartrate.model.AddElderResponse
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.model.ElderListResponse
import com.cloudproject.smartheartrate.network.AccountServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val hostname = "192.168.1.36"
private const val email = "aaa@gmail.com"

class ElderRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://$hostname:5000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getElderList(): MutableLiveData<ArrayList<Elder>> {

        val elderList: MutableLiveData<ArrayList<Elder>> = MutableLiveData<ArrayList<Elder>>()
        val body: HashMap<String, String> = HashMap()
        body["email"] = email

        var req: Call<ElderListResponse> = retrofit.create(AccountServices::class.java).getElderList(body)
        req.enqueue(object : Callback<ElderListResponse?> {
            override fun onResponse(call: Call<ElderListResponse?>, response: Response<ElderListResponse?>) {
                if (response.isSuccessful) {
                    elderList.value = response.body()?.result
                } else {
                    elderList.value = null
                    Log.e("ElderRepo", "onResponse: " + response.errorBody())
                }
            }

            override fun onFailure(call: Call<ElderListResponse?>, t: Throwable) {
                elderList.value = null
                Log.e("ElderRepo", "onFailure: " + t.message)
            }
        })
        return elderList
    }

    fun addElder(elder: Elder): MutableLiveData<Boolean> {
        val addSuccess: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
        val body: HashMap<String, String> = HashMap()
        body["email"] = email
        body["firstName"] = elder.firstName
        body["lastName"] = elder.lastName
        body["age"] = elder.age.toString()
        body["lat"] = elder.lat.toString()
        body["lng"] = elder.lng.toString()

        var req: Call<AddElderResponse> = retrofit.create(AccountServices::class.java).addElder(body)
        req.enqueue(object : Callback<AddElderResponse?> {
            override fun onResponse(call: Call<AddElderResponse?>, response: Response<AddElderResponse?>) {
                if (response.isSuccessful) {
                    addSuccess.value = true
                } else {
                    Log.e("ElderRepo", "onResponse: " + response.errorBody())
                    addSuccess.value = false
                }
            }

            override fun onFailure(call: Call<AddElderResponse?>, t: Throwable) {
                addSuccess.value = false
                Log.e("ElderRepo", "onFailure: " + t.message)
            }
        })
        return addSuccess
    }
}