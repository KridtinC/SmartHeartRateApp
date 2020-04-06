package com.cloudproject.smartheartrate.authen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.cloudproject.smartheartrate.HomeActivity
import com.cloudproject.smartheartrate.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val closeLoginIv = findViewById<ImageView>(R.id.closeRegisterIv)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        closeLoginIv.setOnClickListener(View.OnClickListener {
            super.onBackPressed()
        })

        loginBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }
}
