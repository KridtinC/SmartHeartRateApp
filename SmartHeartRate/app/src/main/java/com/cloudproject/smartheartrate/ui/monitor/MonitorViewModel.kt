package com.cloudproject.smartheartrate.ui.monitor

import android.os.CountDownTimer
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.ui.SharedViewModel
import com.cloudproject.smartheartrate.ui.repository.ElderRepository
import java.util.logging.Handler
import kotlin.random.Random

class MonitorViewModel() : ViewModel() {
    private lateinit var user:String
    private var elderNumb = 3
    private  var elderRate:MutableLiveData<ArrayList<String>> = MutableLiveData()
//    private  var rate :ArrayList<String>
    private var elderName = arrayListOf<String>("MAKI ADE","KOJO UNO","YUI HATANO")

    init {
        elderRate.value = randomRate()
    }

    fun setElderNumb(numb : Int)  {
        elderNumb = numb
        startTimerLoop()
    }
    private fun startTimerLoop() {


        val timer = object:CountDownTimer(200,1) {
            override fun onFinish() {
                elderRate.value = randomRate()

            }

            override fun onTick(millisUntilFinished: Long) {
//                elderRate.value = randomRate()
            }
        }
        timer.start()
    }
    fun getElderRate() : MutableLiveData<ArrayList<String>> {

        return elderRate
    }

    fun randomRate() : ArrayList<String> {
        val out:ArrayList<String> = ArrayList()
        val change = Random.nextBoolean()
        if(change){
            for(each in 1..elderNumb){
                out.add(Random.nextInt(70,200).toString())
            }
        }

        return out
    }
    // send request to business logic

}