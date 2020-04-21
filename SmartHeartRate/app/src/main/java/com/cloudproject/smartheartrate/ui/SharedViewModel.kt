package com.cloudproject.smartheartrate.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.ui.repository.ElderRepository

class SharedViewModel: ViewModel() {
    private var _elderList = MutableLiveData<ArrayList<Elder>>()
    private var _addSuccess = MutableLiveData<Boolean>()
    private var elderRepository = ElderRepository()

    fun getElderList(email: String, isRefresh: Boolean): LiveData<ArrayList<Elder>> {
        if(isRefresh){
            _elderList = elderRepository.getElderList(email)
        }
        return _elderList
    }

    fun addElder(email: String, elder: Elder): MutableLiveData<Boolean> {
        _addSuccess = elderRepository.addElder(email, elder)
        return _addSuccess
    }
}