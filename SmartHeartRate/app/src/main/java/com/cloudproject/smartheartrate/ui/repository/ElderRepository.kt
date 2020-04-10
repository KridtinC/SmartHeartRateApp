package com.cloudproject.smartheartrate.ui.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.model.ElderList
import com.cloudproject.smartheartrate.network.AccountServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val hostname = "192.168.1.36"
private const val email = "aaa@gmail.com"

class ElderRepository {

    private var isSuccess: Boolean = true
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://$hostname:5000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getElderList(): MutableLiveData<ArrayList<Elder>> {

        val elderList: MutableLiveData<ArrayList<Elder>> = MutableLiveData<ArrayList<Elder>>()
        val body: HashMap<String, String> = HashMap()
        body["email"] = email

        var req: Call<ElderList> = retrofit.create(AccountServices::class.java).getElderList(body)
        req.enqueue(object : Callback<ElderList?> {
            override fun onResponse(call: Call<ElderList?>, response: Response<ElderList?>) {
                if (response.isSuccessful) {
                    elderList.value = response.body()?.elderList
                    isSuccess = true
                } else {
                    Log.e("ElderRepo", "onResponse: " + response.errorBody())
                    isSuccess = false
                }
            }

            override fun onFailure(call: Call<ElderList?>, t: Throwable) {
                elderList.value = addExampleData()
                Log.e("ElderRepo", "onFailure: " + t.message)
                isSuccess = false
            }
        })
        return elderList
    }

    private fun addExampleData(): ArrayList<Elder> {
        val data: ArrayList<Elder> = ArrayList()
        data.add(Elder(156487, "Adam", "Smith", 22, 13.145678, 120.147896))
        return data
    }

    fun isFetchElderListSuccess(): MutableLiveData<Boolean> {
        val isSuccessLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
        isSuccessLiveData.value = isSuccess
        return isSuccessLiveData
    }
}