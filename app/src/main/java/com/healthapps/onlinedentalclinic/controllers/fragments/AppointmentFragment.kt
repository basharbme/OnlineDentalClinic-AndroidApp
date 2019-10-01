package com.healthapps.onlinedentalclinic.controllers.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.AppointmentAdapter
import com.healthapps.onlinedentalclinic.controllers.models.DentalAppointment

class AppointmentFragment : Fragment() {

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_appointments, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.RV_dental_appointments)
        recyclerView.layoutManager = LinearLayoutManager(root.context, LinearLayout.VERTICAL, false)
//        Create an arraylist
        val dataList = ArrayList<DentalAppointment>()
        dataList.add(DentalAppointment("1", "1", "Alex Ugarte",
            "Clinica 1", "Curación de muelas", "30/12/19", "3:30 PM"))
        dataList.add(DentalAppointment("2", "2","Jordi Ramos",
            "Clinica 2", "Servicio 2", "24/03/19", "5:00 PM"))
        dataList.add(DentalAppointment("3", "", "Elvis Sams",
            "Clinic 3", "Servicio 3", "11/09/19", "2:20 PM"))
//        pass the values to RvAdapter
        val appAdapter = AppointmentAdapter(dataList)
//        set the recyclerView to the adapter
        recyclerView.adapter = appAdapter

        return root
    }
}