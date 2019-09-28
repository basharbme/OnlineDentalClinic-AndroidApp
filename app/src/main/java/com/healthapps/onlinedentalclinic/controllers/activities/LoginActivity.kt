package com.healthapps.onlinedentalclinic.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healthapps.onlinedentalclinic.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
            } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_login)

        button_login.setOnClickListener{
            email = editText_email.text.toString()
            password = editText_password.text.toString()

            if(email == "sergioespinal@gmail.com" && password == "sergio123"){
                val intent = Intent(this, PatientActivity::class.java)
                startActivity(intent)
            }
        }

        button_register.setOnClickListener {

        }
    }
}
