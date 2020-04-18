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
    private lateinit var elderRate:MutableLiveData<ArrayList<String>>
    private var rate :ArrayList<String> = arrayListOf<String>("100","120","110")
    private var elderName = arrayListOf<String>("MAKI ADE","KOJO UNO","YUI HATANO")

    private var elderRepository = ElderRepository()
    private lateinit var elderLists:ArrayList<Elder>

    fun setElderNumb(numb : Int)  {
        elderNumb = numb
    }

    fun getElderRate() : MutableLiveData<ArrayList<String>> {
        val timer= object :CountDownTimer(3,1){
            override fun onFinish() {
                elderRate.value = randomRate()
            }

            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
            }

        }
        timer.start()

        return elderRate
    }

    fun randomRate() : ArrayList<String> {
        val out:ArrayList<String> = ArrayList()
        val change = Random.nextBoolean()
        if(change){
            for(each in 1..elderNumb){
                out.add(Random.nextInt(70,200).toString())
            }
            rate = out
        }

        return rate
    }
    // send request to business logic

}