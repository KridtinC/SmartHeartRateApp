package com.cloudproject.smartheartrate.authen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloudproject.smartheartrate.ui.repository.AuthenRepository

class RegisterViewModel: ViewModel() {
    private var authenRepository = AuthenRepository()
    private var _registerSuccess = MutableLiveData<String>()

    fun register(email: String, password: String, firstName: String, lastName: String): MutableLiveData<String> {
        _registerSuccess = authenRepository.register(email, password, firstName, lastName)
        return _registerSuccess
    }
}