package com.cloudproject.smartheartrate.ui.monitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MonitorViewModel(savedStateHandle : SavedStateHandle) : ViewModel() {
    private lateinit var user:String
    private val userid:String = savedStateHandle["uid"]?: throw IllegalArgumentException("missing user id")
    private var eldernumb = 3
    private var eldername = arrayListOf<String>("MAKI ADE","KOJO UNO","YUI HATANO")
    private var elderrate = arrayListOf<String>("100","120","110")
    fun getElderNumber(): Int {
        return eldernumb
    }
    fun getElderName() : ArrayList<String> {
        return this.eldername
    }
    fun getElderRate() : ArrayList<String>{
        return this.elderrate
    }

    // send request to business logic

}