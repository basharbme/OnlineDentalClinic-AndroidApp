package com.healthapps.onlinedentalclinic.controllers.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.models.Odontogram
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.activity_create_odontogram.*

class CreateOdontogramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_odontogram)


        textViewPatient.setOnClickListener {
            val intent = Intent(this, PatientActivity::class.java)

            MainActivity.textViewPerson = textViewPatient

            startActivity(intent)
        }

        textViewClinic.setOnClickListener {
            val intent = Intent(this, ClinicActivity::class.java)

            MainActivity.textViewClinic = textViewClinic

            startActivity(intent)
        }

        button_save_odontogram.setOnClickListener {
            val odontogram = Odontogram()

            if(MainActivity.textViewPerson != null && MainActivity.textViewClinic != null){
                odontogram.dentists_id = MainActivity.currentUser
                odontogram.patients_id = MainActivity.person
                odontogram.clinics_id = MainActivity.clinic
            }

            OnlineDentalClinicAPI.saveOdontograms(
                odontograms = odontogram,
                responseHandler = {
                    Log.d("Odontograms", it.toString())
                },
                responseError = {
                    Log.d(
                        "Error",
                        "Error $it.errorCode: $it.errorBody $it.localizedMessage"
                    )
                },
                token = getString(R.string.token)
            )

            val intentResult = Intent()

            intentResult.putExtra("odontograms", odontogram)
            setResult(Activity.RESULT_OK, intentResult)

            finish()
        }
    }
}
