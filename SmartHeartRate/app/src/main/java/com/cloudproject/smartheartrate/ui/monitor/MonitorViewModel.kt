package com.cloudproject.smartheartrate.ui.monitor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MonitorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is monitor Fragment"
    }
    val text: LiveData<String> = _text
}