package com.cloudproject.smartheartrate.ui.monitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonitorViewModel : ViewModel() {

    private var eldernumb = 3
    private var eldername = arrayListOf<String>("MAKI ADE","KOJO UNO","YUI HATANO")
    private var elderrate = arrayListOf<Int>(100,120,110)
    private var adapter:RecyclerAdapter = RecyclerAdapter(eldername,elderrate)

    public  fun getElderNumber(): Int {
        return eldernumb
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is monitor Fragment"
    }

    val text: LiveData<String> = _text
    fun ViewGroup.inflate(layoutRes:Int,attachToRoot:Boolean=false):View {
        return LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)
    }
}