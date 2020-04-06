package com.cloudproject.smartheartrate.authen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.cloudproject.smartheartrate.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val closeRegisterIv = findViewById<ImageView>(R.id.closeRegisterIv)
        closeRegisterIv.setOnClickListener(View.OnClickListener {
            super.onBackPressed()
        })
    }
}
