package com.healthapps.onlinedentalclinic.controllers.activities

import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.gson.Gson
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.fragments.AppointmentFragment
import com.healthapps.onlinedentalclinic.controllers.fragments.CreateAppointmentFragment
import com.healthapps.onlinedentalclinic.controllers.models.Clinic
import com.healthapps.onlinedentalclinic.controllers.models.Person
import com.healthapps.onlinedentalclinic.controllers.models.Service



class MainActivity : AppCompatActivity() {

    companion object{
        var clinic: Clinic = Clinic()
        var dentist: Person = Person()
        var service: Service = Service()
        var patient: Person = Person()
        var textViewClinic: TextView? = null
        var textViewDentist: TextView? = null
        var textViewService: TextView? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_appointments,
                R.id.navigation_dental_info,
                R.id.navigation_sales,
                R.id.navigation_help,
                R.id.navigation_account
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if(intent.hasExtra("user")){
            //val jsonPatient = JSONObject(intent.getSerializableExtra("user").toString())
            //Log.d("josnPatient", jsonPatient.toString())
            val gson = Gson()

            patient = gson.fromJson(intent.getSerializableExtra("user")?.toString(), Person::class.java)
        }
    }

    /*override fun onBackPressed() {
        super.onBackPressed()
        button!!.visibility = View.VISIBLE

    }*/
}