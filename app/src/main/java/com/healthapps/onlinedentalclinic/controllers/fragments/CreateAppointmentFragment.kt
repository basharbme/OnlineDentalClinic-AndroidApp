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
import com.healthapps.onlinedentalclinic.controllers.activities.ClinicActivity
import com.healthapps.onlinedentalclinic.controllers.activities.PersonActivity
import com.healthapps.onlinedentalclinic.controllers.activities.MainActivity
import com.healthapps.onlinedentalclinic.controllers.activities.ServiceActivity
import com.healthapps.onlinedentalclinic.models.DentalAppointment
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.fragment_create_appointment.*
import java.text.SimpleDateFormat
import java.util.*


class CreateAppointmentFragment(private val listener: ClickListener) : Fragment() {

    private var root: View? = null

    open interface ClickListener {
        fun onClick(save: Boolean)

    }

    companion object {
        var clickListener: ClickListener? = null
    }

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
            MainActivity.textViewClinic = textViewClinic
            activity!!.startActivity(Intent(activity, ClinicActivity::class.java))
        }

        textViewDentist.setOnClickListener {
            MainActivity.textViewPerson = textViewDentist
            activity!!.startActivity(Intent(activity, PersonActivity::class.java))
        }

        textViewService.setOnClickListener {
            MainActivity.textViewService = textViewService
            activity!!.startActivity(Intent(activity, ServiceActivity::class.java))
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

        button_save_appointment.setOnClickListener {
            val dentalAppointment = DentalAppointment()
            clickListener = listener

            dentalAppointment.patients_id = MainActivity.currentUser
            dentalAppointment.dentists_id = MainActivity.person
            dentalAppointment.clinics_id = MainActivity.clinic
            dentalAppointment.services_id = MainActivity.service
            dentalAppointment.date = textViewDate.text.toString()
            dentalAppointment.hour = textViewHour.text.toString()

            val appointmentFragment = AppointmentFragment()
            val transactionFragment: FragmentTransaction = fragmentManager!!.beginTransaction()

            //transactionFragment.replace(R.id.nav_host_fragment, appointmentFragment)
            transactionFragment.remove(this)
            transactionFragment.commit()
            fragmentManager!!.popBackStack()

            OnlineDentalClinicAPI.saveAppointments(
                dentalAppointments = dentalAppointment,
                responseHandler = {
                    Log.d("Save appointment", it.toString())
                    if (clickListener != null) {
                        clickListener?.onClick(true)
                    }
                },
                responseError = {
                    Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
                },
                token = getString(R.string.token)
            )
            Log.d("Dental appointments", dentalAppointment.toString())
        }
    }
}
