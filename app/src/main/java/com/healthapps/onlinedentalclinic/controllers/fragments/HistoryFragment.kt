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
import com.healthapps.onlinedentalclinic.controllers.adapters.HistoryAdapter
import com.healthapps.onlinedentalclinic.models.DentalRecords
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.fragment_records.*

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.RV_dental_records)

        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getDentalRecords(
            responseHandler = {
                //filter
                val person = Person()
                person.email = "healthappscompany@gmail.com"
                person.password = "sergio1espinal"

                val dataList: ArrayList<DentalRecords> = it as ArrayList<DentalRecords>

                dataList.filter { user -> user.patients_id.email == person.email &&
                        user.patients_id.password == person.password }
                //pass the values to RvAdapter
                val appAdapter = HistoryAdapter(dataList, object : HistoryAdapter.ClickListener {
                    override fun onClick(position: Int) {
                        //appointmentSelected = dataList[position]
                        Log.d("Clicked from adapter", "Here fragment")
                        dataList.removeAt(position)
                        recyclerView.adapter!!.notifyItemRemoved(position)
                        recyclerView.adapter!!.notifyItemRangeChanged(position, dataList.size)
                        recyclerView.adapter!!.notifyDataSetChanged()
                        //recyclerView.adapter!!.notifyItemChanged(position)
                    }
                })
                //set the recyclerView to the adapter
                recyclerView.adapter = appAdapter
            },
            responseError = {
                Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
            },
            token = getString(R.string.token)
        )

        record_floating_action_button.setOnClickListener {
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