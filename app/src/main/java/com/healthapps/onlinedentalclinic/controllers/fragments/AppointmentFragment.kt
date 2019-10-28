package com.healthapps.onlinedentalclinic.controllers.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.RV_dental_appointments)

        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getDentalAppointments(
            responseHandler = {
                //filter
                val person = Person()
                person.email = "healthappscompany@gmail.com"
                person.password = "sergio1espinal"

                val dataList: ArrayList<DentalAppointment> = it as ArrayList<DentalAppointment>

                dataList.filter { user -> user.patients_id.email == person.email &&
                        user.patients_id.password == person.password }
                //pass the values to RvAdapter
                val appAdapter = AppointmentAdapter(dataList, object : AppointmentAdapter.ClickListener {
                    override fun onClick(position: Int) {
                        //appointmentSelected = dataList[position]
                        Log.d("Clicked from adapter", "Here fragment")
                        dataList.removeAt(position)
                        recyclerView.adapter!!.notifyItemRemoved(position)
                        recyclerView.adapter!!.notifyItemRangeChanged(position, dataList.size)
                        recyclerView.adapter!!.notifyDataSetChanged()
                        //recyclerView.adapter!!.notifyItemChanged(position)
                    }
                },
                    token = getString(R.string.token))
               //set the recyclerView to the adapter
                recyclerView.adapter = appAdapter
            },
            responseError = {
                Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
            },
            token = getString(R.string.token)
        )

        floating_action_button.setOnClickListener {
            val createAppointments = CreateAppointmentFragment(object : CreateAppointmentFragment.ClickListener {
                override fun onClick(save: Boolean) {
                    if(save){
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                }
            })
            val fragmentTransaction: FragmentTransaction = getFragmentManager()!!.beginTransaction()

            fragmentTransaction.replace(R.id.nav_host_fragment, createAppointments)
            fragmentTransaction.setPrimaryNavigationFragment(createAppointments)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}