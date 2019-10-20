package com.healthapps.onlinedentalclinic.controllers.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.activities.PatientActivity
import com.healthapps.onlinedentalclinic.controllers.adapters.ClinicAdapter
import com.healthapps.onlinedentalclinic.controllers.models.Clinic
import com.healthapps.onlinedentalclinic.controllers.networking.OnlineDentalClinicAPI


class ClinicFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clinic, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.clinics_list)
        var clinic = (activity as PatientActivity).clinic

        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)

        OnlineDentalClinicAPI.getClincs(
            responseHandler = {
                val dataList: ArrayList<Clinic> = it as ArrayList<Clinic>
                val adapter = ClinicAdapter(dataList, object : ClinicAdapter.ClickListener{
                    override fun onClick(position: Int) {
                        val clinicSelected = dataList[position]
                        Toast.makeText(context, "clicked on " + clinicSelected.name, Toast.LENGTH_SHORT).show()
                        clinic = clinicSelected
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