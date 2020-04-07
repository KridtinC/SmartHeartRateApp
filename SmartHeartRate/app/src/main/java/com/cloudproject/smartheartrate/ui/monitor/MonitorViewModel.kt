package com.cloudproject.smartheartrate.ui.monitor

import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonitorViewModel : ViewModel() {

    private lateinit var view:View
    private val _text = MutableLiveData<String>().apply {
        value = "This is monitor Fragment"
    }
    val text: LiveData<String> = _text

}