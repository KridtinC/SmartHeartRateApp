package com.cloudproject.smartheartrate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.cloudproject.smartheartrate.authen.LoginActivity
import com.cloudproject.smartheartrate.authen.RegisterActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginPressed = findViewById<Button>(R.id.loginBtn)
        val registerPressed = findViewById<Button>(R.id.registerBtn)

        loginPressed.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        registerPressed.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}
