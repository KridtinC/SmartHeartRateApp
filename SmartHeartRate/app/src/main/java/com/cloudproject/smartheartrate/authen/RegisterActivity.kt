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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cloudproject.smartheartrate.HomeActivity
import com.cloudproject.smartheartrate.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        val closeRegisterIv = findViewById<ImageView>(R.id.closeRegisterIv)
        closeRegisterIv.setOnClickListener(View.OnClickListener {
            super.onBackPressed()
        })

        val registerBtn = findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener {
            val email = registerEmail.text.toString()
            val password = registerPassword.text.toString()
            val confirmPassword = registerConfirmPassword.text.toString()
            val firstName = registerFirstName.text.toString()
            val lastName = registerLastName.text.toString()
            if (password != confirmPassword){
                Toast.makeText(this, "Register failed: password do not match", Toast.LENGTH_SHORT).show()
            }
            else{
                registerViewModel.register(email, password, firstName, lastName).observe(this, Observer { item ->
                    if (item != null){
                        if (item == "OK"){
                            Toast.makeText(this, "Register successful, user: $email", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Register failed: $item", Toast.LENGTH_SHORT).show()
                        }

                    }
                    else{
                        Toast.makeText(this, "Register failed with no reason", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}
