package com.healthapps.onlinedentalclinic.controllers.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction

import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.activities.PatientActivity
import kotlinx.android.synthetic.main.fragment_create_appointment.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min


class CreateAppointmentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_appointment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var clinic = (activity as PatientActivity).clinic

        //Calendar
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get((Calendar.DAY_OF_MONTH))

        textViewClinic.setOnClickListener {
            val clinicFragment = ClinicFragment()
            val fragmentTransaction: FragmentTransaction = getFragmentManager()!!.beginTransaction()

            fragmentTransaction.replace(R.id.nav_host_fragment, clinicFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        textViewDate.setOnClickListener{
            val dpd = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener{
                view, cyear, cmonth, cday -> textViewDate.text = "" + cday + "/" + cmonth + "/" + cyear;
            }, year, month, day)

            dpd.show()
        }

        textViewHour.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)

                textViewHour.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(view.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false).show()

            Log.d("Clinic object", clinic.toString())
        }
    }
}
