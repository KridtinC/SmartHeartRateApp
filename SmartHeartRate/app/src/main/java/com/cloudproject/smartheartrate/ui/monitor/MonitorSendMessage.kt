package com.cloudproject.smartheartrate.ui.monitor

import android.os.Message
import android.provider.Settings.Secure.getString
import android.util.Log
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient
import com.cloudproject.smartheartrate.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.events.Publisher
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MonitorSendMessage : FirebaseMessagingService() {
//    private lateinit var message: FirebaseInstanceId
//    val t :AmazonS
    fun connect() {
        val aws :AWSAppSyncClient
    }
    fun getToken()  {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener {task->

            if(!task.isSuccessful){
                Log.d("Message","GetInstanceFailed",task.exception)

            }else {
                val token = task.result?.token
                Log.d("Message",token)
            }
        })

//        FirebaseMessaging.getInstance().send()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.data.isNotEmpty().let{
            Log.d("Message","Message data payload"+message.data)
        }
        message.notification?.let{
            Log.d("Message","Message Notification Body: ${it.body}")
        }

    }



}