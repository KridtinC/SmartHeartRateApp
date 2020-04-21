package com.cloudproject.smartheartrate.authen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudproject.smartheartrate.HomeActivity
import com.cloudproject.smartheartrate.R
import com.cloudproject.smartheartrate.ui.monitor.MonitorViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val closeLoginIv = findViewById<ImageView>(R.id.closeRegisterIv)
        val loginBtn = findViewById<Button>(R.id.loginBtn)

        closeLoginIv.setOnClickListener(View.OnClickListener {
            super.onBackPressed()
        })

        loginBtn.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            loginViewModel.login(email, password).observe(this, Observer { item ->
                if (item != null){
                    if (item == "OK"){
                        Toast.makeText(this, "Login successful, user: $email", Toast.LENGTH_SHORT).show()
                        val sharedPreferences: SharedPreferences= application.getSharedPreferences("Authentication", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putString("email", email).apply()
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "Login failed: $item", Toast.LENGTH_SHORT).show()
                    }

                }
                else{
                    Toast.makeText(this, "Login failed with no reason", Toast.LENGTH_SHORT).show()
                }
            })

        }

    }
}
