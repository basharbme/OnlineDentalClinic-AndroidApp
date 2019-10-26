package com.healthapps.onlinedentalclinic.controllers.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction

import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.activities.ClinicActivity
import com.healthapps.onlinedentalclinic.controllers.activities.DentistActivity
import com.healthapps.onlinedentalclinic.controllers.activities.PatientActivity
import com.healthapps.onlinedentalclinic.controllers.activities.ServiceActivity
import com.healthapps.onlinedentalclinic.controllers.models.DentalAppointment
import com.healthapps.onlinedentalclinic.controllers.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.adapter_clinic.*
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
            PatientActivity.textViewClinic = textViewClinic
            activity!!.startActivity(Intent(activity, ClinicActivity::class.java))
        }

        textViewDentist.setOnClickListener {
            PatientActivity.textViewDentist = textViewDentist
            activity!!.startActivity(Intent(activity, DentistActivity::class.java))
        }

        textViewService.setOnClickListener {
            PatientActivity.textViewService = textViewService
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

            dentalAppointment.patients_id = PatientActivity.patient
            dentalAppointment.dentists_id = PatientActivity.dentist
            dentalAppointment.clinics_id = PatientActivity.clinic
            dentalAppointment.services_id = PatientActivity.service
            dentalAppointment.date = textViewDate.text.toString()
            dentalAppointment.hour = textViewHour.text.toString()

            val appointmentFragment = AppointmentFragment()
            val transactionFragment: FragmentTransaction = fragmentManager!!.beginTransaction()

            transactionFragment.replace(R.id.nav_host_fragment, appointmentFragment)
            transactionFragment.commit()
            fragmentManager!!.popBackStack()

            OnlineDentalClinicAPI.saveAppoitments(
                dentalAppointments = dentalAppointment,
                responseHandler = {
                    Log.d("Save appointment", it.toString())
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
