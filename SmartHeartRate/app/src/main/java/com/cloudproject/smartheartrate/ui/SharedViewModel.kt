package com.cloudproject.smartheartrate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.ui.repository.ElderRepository

class SharedViewModel: ViewModel() {
    private var _elderList = MutableLiveData<ArrayList<Elder>>()
    private var _addSuccess = MutableLiveData<Boolean>()
    private var elderRepository = ElderRepository()

    fun getElderList(): LiveData<ArrayList<Elder>> {
        _elderList = elderRepository.getElderList()
        return _elderList
    }

    fun addElder(elder: Elder): MutableLiveData<Boolean> {
        _addSuccess = elderRepository.addElder(elder)
        return _addSuccess
    }
}