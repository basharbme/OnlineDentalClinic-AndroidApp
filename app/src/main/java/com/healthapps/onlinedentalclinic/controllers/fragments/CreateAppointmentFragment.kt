package com.healthapps.onlinedentalclinic.controllers.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
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


class CreateAppointmentFragment : Fragment() {
    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_create_appointment, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Calendar
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get((Calendar.DAY_OF_MONTH))

        textViewClinic.setOnClickListener {
            val clinicFragment = ClinicFragment()
            val fragmentTransaction: FragmentTransaction = childFragmentManager!!.beginTransaction()
            fragmentTransaction.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
            fragmentTransaction.replace(R.id.fragment_create_appointments, clinicFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            (activity as PatientActivity).textViewClinic = textViewClinic
            (activity as PatientActivity).button = button_save_appointment
            button_save_appointment.visibility = View.INVISIBLE
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
        }
    }
}
