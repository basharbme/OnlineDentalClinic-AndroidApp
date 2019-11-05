package com.healthapps.onlinedentalclinic.controllers.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.activities.CreateDentalRecordActivity
import com.healthapps.onlinedentalclinic.controllers.adapters.DentalRecordAdapter
import com.healthapps.onlinedentalclinic.models.DentalRecords
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import com.healthapps.onlinedentalclinic.utils.SinglentonOnClick
import kotlinx.android.synthetic.main.fragment_records.*

class DentalRecordFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var dataList: ArrayList<DentalRecords>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.RV_dental_records)

        recyclerView!!.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getDentalRecords(
            responseHandler = {
                //filter
                val person = Person()
                person.email = "healthappscompany@gmail.com"
                person.password = "sergio1espinal"

                dataList = it as ArrayList<DentalRecords>

                dataList!!.filter { user -> user.patients_id.email == person.email &&
                        user.patients_id.password == person.password }
                //pass the values to RvAdapter
                val appAdapter = DentalRecordAdapter(dataList!!, object : DentalRecordAdapter.ClickListener {
                    override fun onClick(position: Int) {
                        Log.d("Clicked from adapter", "Here fragment")
                        /*dataList.removeAt(position)
                        recyclerView.adapter!!.notifyItemRemoved(position)
                        recyclerView.adapter!!.notifyItemRangeChanged(position, dataList.size)
                        recyclerView.adapter!!.notifyDataSetChanged()*/
                    }
                })
                //set the recyclerView to the adapter
                recyclerView!!.adapter = appAdapter
            },
            responseError = {
                Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
            },
            token = getString(R.string.token)
        )

        record_floating_action_button.setOnClickListener {
            val requestCode = 1

            startActivityForResult(Intent(activity, CreateDentalRecordActivity::class.java), requestCode)
            // activity!!.startActivity(Intent(activity, CreateDentalRecordActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1){
            val pos = dataList!!.size

            if( resultCode == RESULT_OK){
                if (data != null){
                    dataList!!.add(data!!.extras!!.getSerializable("dentalRecords") as DentalRecords)
                    recyclerView!!.adapter!!.notifyItemChanged(pos + 1, dataList)
                }
            }
        }
    }
}