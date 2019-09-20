package com.healthapps.onlinedentalclinic.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.healthapps.onlinedentalclinic.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
            } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_login)
    }
}
