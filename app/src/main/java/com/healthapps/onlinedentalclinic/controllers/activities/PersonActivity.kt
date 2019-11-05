package com.healthapps.onlinedentalclinic.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.PersonAdapter
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        val recyclerView = findViewById<RecyclerView>(R.id.dentists_list)
        val isPatient: Boolean = intent.extras!!.getBoolean("isPatient")

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getPeople(
            responseHandler = {
                Log.d("Is Current user", isPatient.toString())
                val dataList: ArrayList<Person> = it as ArrayList<Person>
                val adapter = PersonAdapter(dataList, object : PersonAdapter.ClickLister{
                    override fun onClick(position: Int) {
                        val selectPerson = dataList[position]
                        MainActivity.person = selectPerson
                        MainActivity.textViewPerson!!.text = selectPerson.fullname
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
