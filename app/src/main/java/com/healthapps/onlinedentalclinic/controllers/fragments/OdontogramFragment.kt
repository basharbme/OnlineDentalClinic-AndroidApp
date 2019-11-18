package com.healthapps.onlinedentalclinic.controllers.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.activities.CreateDentalPieceActivity
import com.healthapps.onlinedentalclinic.controllers.activities.CreateOdontogramActivity
import com.healthapps.onlinedentalclinic.controllers.adapters.OdontogramAdapter
import com.healthapps.onlinedentalclinic.models.Odontogram
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.fragment_appointments.*

class OdontogramFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var dataList: ArrayList<Odontogram>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_odontograms, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.RV_odontograms)

        recyclerView!!.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        OnlineDentalClinicAPI.getOdontograms(
            responseHandler = {
                //filter
                val person = Person()
                person.email = "healthappscompany@gmail.com"
                person.password = "sergio1espinal"

                dataList = it as ArrayList<Odontogram>

                dataList!!.filter { user -> user.patients_id.email == person.email &&
                        user.patients_id.password == person.password }

                //pass the values to RvAdapter
                val appAdapter = OdontogramAdapter(dataList!!, object : OdontogramAdapter.ClickLister {
                    override fun onClick(position: Int) {
                        Log.d("Clicked from adapter", "Here fragment")
                        //recyclerView.adapter!!.notifyItemChanged(position)

                        val requestCode = 3
                        val intentSend = Intent(activity, CreateDentalPieceActivity::class.java)

                        intentSend.putExtra("odontogram", dataList!![position])
                        startActivityForResult(intentSend, requestCode)

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

        floating_action_button.setOnClickListener {
            val requestCode = 2

            startActivityForResult(Intent(activity, CreateOdontogramActivity::class.java), requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2){
            val pos = dataList!!.size

            if( resultCode == RESULT_OK){
                if (data != null){
                    dataList!!.add(data.extras!!.getSerializable("odontograms") as Odontogram)
                    recyclerView!!.adapter!!.notifyItemChanged(pos + 1, dataList)
                }
            }
        }
    }

}
