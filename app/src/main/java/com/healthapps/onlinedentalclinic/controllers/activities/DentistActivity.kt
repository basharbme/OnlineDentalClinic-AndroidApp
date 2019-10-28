package com.healthapps.onlinedentalclinic.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.DentistAdapter
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI

class DentistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dentist)

        val recyclerView = findViewById<RecyclerView>(R.id.dentists_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getDentists(
            responseHandler = {
                val dataList: ArrayList<Person> = it as ArrayList<Person>
                val adapter = DentistAdapter(dataList, object : DentistAdapter.ClickLister{
                    override fun onClick(position: Int) {
                        val dentistSelect = dataList[position]
                        MainActivity.dentist = dentistSelect
                        MainActivity.textViewDentist!!.text = dentistSelect.fullname
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
