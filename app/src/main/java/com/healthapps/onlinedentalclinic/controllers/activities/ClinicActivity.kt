package com.healthapps.onlinedentalclinic.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.ClinicAdapter
import com.healthapps.onlinedentalclinic.models.Clinic
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI

class ClinicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic)

        val recyclerView = findViewById<RecyclerView>(R.id.clinics_list)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getClinics(
            responseHandler = {
                val dataList: ArrayList<Clinic> = it as ArrayList<Clinic>
                val adapter = ClinicAdapter(dataList, object : ClinicAdapter.ClickListener{
                    override fun onClick(position: Int) {
                        val clinicSelected = dataList[position]
                        MainActivity.clinic = clinicSelected
                        MainActivity.textViewClinic!!.text = clinicSelected.name
                        //Toast.makeText(this@ClinicActivity, "clicked on " + clinicSelected.name, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                })

                recyclerView.adapter = adapter
            },
            responseError = {
                Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
            },
            token = getString(R.string.token)
        )
    }
}
