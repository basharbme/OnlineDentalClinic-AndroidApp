package com.healthapps.onlinedentalclinic.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.ServiceAdapter
import com.healthapps.onlinedentalclinic.controllers.models.Service
import com.healthapps.onlinedentalclinic.controllers.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.fragment_create_appointment.*

class ServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val recyclerView = findViewById<RecyclerView>(R.id.services_list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getService(
            responseHandler = {
                val dataList: ArrayList<Service> = it as ArrayList<Service>
                val adapter = ServiceAdapter(dataList, object : ServiceAdapter.ClickListener{
                    override fun onClick(position: Int) {
                        val serviceSelect = dataList[position]
                        PatientActivity.service = serviceSelect
                        PatientActivity.textViewService!!.text = serviceSelect.name
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
