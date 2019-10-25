package com.healthapps.onlinedentalclinic.controllers.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.activities.PatientActivity
import com.healthapps.onlinedentalclinic.controllers.adapters.ServiceAdapter
import com.healthapps.onlinedentalclinic.controllers.models.Service
import com.healthapps.onlinedentalclinic.controllers.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.fragment_create_appointment.*

class ServiceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.services_list)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)

        OnlineDentalClinicAPI.getService(
            responseHandler = {
                val dataList: ArrayList<Service> = it as ArrayList<Service>
                val adapter = ServiceAdapter(dataList, object : ServiceAdapter.ClickListener{
                    override fun onClick(position: Int) {
                        val serviceSelect = dataList[position]
                        val me: Fragment = this@ServiceFragment

                        (activity as PatientActivity).service = serviceSelect
                        (activity as PatientActivity).textViewService!!.text = serviceSelect.name
                        fragmentManager!!.beginTransaction().remove(me).commit()
                        fragmentManager!!.popBackStack()
                        //(activity as PatientActivity).button!!.visibility = View.VISIBLE
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
