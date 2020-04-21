package com.cloudproject.smartheartrate.authen

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloudproject.smartheartrate.ui.repository.AuthenRepository

class LoginViewModel: ViewModel() {

    private var authenRepository = AuthenRepository()
    private var _loginSuccess = MutableLiveData<String>()

    fun login(email: String, password: String): MutableLiveData<String> {
        _loginSuccess = authenRepository.login(email, password)
        return _loginSuccess
    }
}