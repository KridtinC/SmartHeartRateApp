package com.cloudproject.smartheartrate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getSharedPreferences("Authentication", Context.MODE_PRIVATE).getString("email", null) != null){
            Handler().postDelayed({
                startActivity(Intent(this, HomeActivity::class.java))
                finishAffinity()
            }, 2000)
        }
        else{
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }, 2000)
        }

    }
}