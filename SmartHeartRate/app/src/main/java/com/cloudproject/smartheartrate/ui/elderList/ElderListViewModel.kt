package com.cloudproject.smartheartrate.ui.elderList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ElderListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is elder list Fragment"
    }
    val text: LiveData<String> = _text
}