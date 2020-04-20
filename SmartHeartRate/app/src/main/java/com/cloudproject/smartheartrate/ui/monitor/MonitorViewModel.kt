package com.cloudproject.smartheartrate.ui.monitor


import android.util.Log

import androidx.lifecycle.*
import com.google.firebase.iid.FirebaseInstanceId

import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList
import kotlin.random.Random

class MonitorViewModel() : ViewModel() {

    private var elderNumb = 0
    private  var timer:Timer = Timer()
    private  var elderRate:MutableLiveData<ArrayList<String>> = MutableLiveData()
    private lateinit var rec :ArrayList<String>
//    private  var rate :ArrayList<String>
    private var elderName = arrayListOf<String>("MAKI ADE","KOJO UNO","YUI HATANO")

    fun setElderNumb(numb : Int)  {
        elderNumb = numb
        startTimerLoop()
        rec = ArrayList()
        for(i in 1 until elderNumb+1) {
//            Log.d("value",i.toString())
            rec.add(Random.nextInt(70,120).toString())
        }
    }
    private fun startTimerLoop() {
        timer.scheduleAtFixedRate(
            object:TimerTask() {
            override fun run() {
                elderRate.postValue(randomRate())
            }
        },0,2000)
    }
    fun getElderRate() : MutableLiveData<ArrayList<String>> {

        return elderRate
    }

    fun randomRate() : ArrayList<String> {
        val out:ArrayList<String> = ArrayList()

        for(value in rec){
//            Log.d("Value",value)
            if(Random.nextBoolean()){
                out.add(Random.nextInt(70, 180).toString())
            }else {
                out.add(value)
            }
        }

        rec = out
        Log.d("ElderRateValue",out.toString())
        return rec
    }
    // send request to business logic

}