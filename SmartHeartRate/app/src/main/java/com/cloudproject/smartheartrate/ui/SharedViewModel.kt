package com.cloudproject.smartheartrate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloudproject.smartheartrate.model.Elder
import com.cloudproject.smartheartrate.ui.repository.ElderRepository

class SharedViewModel: ViewModel() {
    private var _elderList = MutableLiveData<ArrayList<Elder>>()
    private var _isSuccess = MutableLiveData<Boolean>()
    private var elderRepository = ElderRepository()

    fun getElderList(): LiveData<ArrayList<Elder>> {
        _elderList = elderRepository.getElderList()
        return _elderList
    }

    fun isFetchElderListSuccess(): LiveData<Boolean> {
        _isSuccess = elderRepository.isFetchElderListSuccess()
        return _isSuccess
    }
}