package com.cloudproject.smartheartrate.ui.monitor

import android.provider.Settings.Secure.getString
import android.util.Log
import com.cloudproject.smartheartrate.R
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService

class MonitorSendMessage {
//    private lateinit var message: FirebaseInstanceId

    fun getToken()  {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener{task->
            if(!task.isSuccessful){
                Log.d("Message","GetInstanceFailed",task.exception)

            }else {
                val token = task.result?.token
                Log.d("Message",token)
            }
        }

    }




}