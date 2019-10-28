package com.healthapps.onlinedentalclinic.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var email: String = ""
    private var password: String = ""
    private var validEmail: Boolean = false
    private var validPassword : Boolean = false
    private var person: Person =
        Person()
    private val emailPattern = Patterns.EMAIL_ADDRESS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
            } catch (e: NullPointerException) {
            Log.d("Error", e.toString())
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

            person.email = email
            person.password = password

            if(validEmail && validPassword){
                OnlineDentalClinicAPI.login(
                    person = person,
                    responseHandler = {
                        Log.d("User accepted", "$it")
                        textGeneralError.text = ""

                        val intent = Intent(this, MainActivity::class.java)

                        it?.apply {
                            finish()
                            intent.putExtra("user", it?.toString())
                            startActivity(intent)
                        }
                    },
                    responseError = {
                        val str: String = it.toString()

                        if("Password incorrect" in str){
                            textGeneralError.text = getString(R.string.textGeneralError)
                            Log.d("Error password", "Password incorrect")
                        }else if("[]" in str){
                            textGeneralError.text = getString(R.string.textGeneralError)
                            Log.d("Error email", "User not found")
                        }
                    },
                    token = getString(R.string.token)
                )
            }
        }

        button_register.setOnClickListener {

        }
    }

    private fun validateEmail(email: String): Boolean{
        return emailPattern.matcher(email).matches()
    }
}
