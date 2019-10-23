package com.healthapps.onlinedentalclinic.controllers.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.AppointmentAdapter
import com.healthapps.onlinedentalclinic.controllers.models.DentalAppointment
import com.healthapps.onlinedentalclinic.controllers.models.Person
import com.healthapps.onlinedentalclinic.controllers.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.fragment_appointments.*



class AppointmentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_appointments, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.RV_dental_appointments)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayout.VERTICAL, false)

        OnlineDentalClinicAPI.getDentalAppointments(
            responseHandler = {
                //filter
                val person = Person()
                person.email = "healthappscompany@gmail.com"
                person.password = "sergio1espinal"

                val datalist: ArrayList<DentalAppointment> = it as ArrayList<DentalAppointment>
                datalist.filter { user -> user.patients_id.email == person.email &&
                        user.patients_id.password == person.password }
                //pass the values to RvAdapter
                val appAdapter = AppointmentAdapter(datalist)
               //set the recyclerView to the adapter
                recyclerView.adapter = appAdapter
            },
            responseError = {
                Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
            },
            token = getString(R.string.token)
        )

        floating_action_button.setOnClickListener {
            val createAppointments = CreateAppointmentFragment()
            val fragmentTransaction: FragmentTransaction = getFragmentManager()!!.beginTransaction()

            fragmentTransaction.replace(R.id.nav_host_fragment, createAppointments)
            fragmentTransaction.setPrimaryNavigationFragment(createAppointments)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}