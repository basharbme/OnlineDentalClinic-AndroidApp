package com.healthapps.onlinedentalclinic.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.healthapps.onlinedentalclinic.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var email: String = ""
    private var password: String = ""
    private var validEmail: Boolean = false
    private var validPassword : Boolean = false
    private val emailPattern = Patterns.EMAIL_ADDRESS

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

            email.apply {
                if(this.isBlank()){
                    textEmailError.text = getString(R.string.textEmailError)
                }else{
                    textEmailError.text = ""
                    if(validateEmail(this)){
                        validEmail = true
                    }else{
                        textEmailError.text = getString(R.string.textInvalidEmail)
                    }
                }
            }
            password.apply {
                if(this.isBlank()){
                    textPasswordError.text = getString(R.string.textPasswordError)
                }else{
                    textPasswordError.text = ""
                    validPassword = true
                }
            }

            if(email == "sergioespinal@gmail.com" && password == "sergio123"){
                val intent = Intent(this, PatientActivity::class.java)
                startActivity(intent)
            }
        }

        button_register.setOnClickListener {

        }
    }

    private fun validateEmail(email: String): Boolean{
        return emailPattern.matcher(email).matches()
    }
}
