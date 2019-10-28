package com.healthapps.onlinedentalclinic.controllers.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.models.DentalAppointment
import com.healthapps.onlinedentalclinic.controllers.networking.OnlineDentalClinicAPI

class AppointmentAdapter(private val dentalAppointmentsList: ArrayList<DentalAppointment>,
                         private val listener: ClickListener, private val token: String) :
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    var context: Context? = null

    open interface ClickListener {
        fun onClick(position: Int)

    }

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_appointment, p0, false)
        context = p0?.context
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dentalAppointmentsList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        clickListener = listener

        val date = dentalAppointmentsList[p1].date
        val partsDate = date.split(Regex("/"))

        p0.day?.text = partsDate[0]
        p0.month?.text = partsDate[1]
        p0.year?.text = partsDate[2]
        p0.time?.text = dentalAppointmentsList[p1].hour
        p0.dentist?.text = dentalAppointmentsList[p1].dentists_id.fullname
        p0.clinic?.text = dentalAppointmentsList[p1].clinics_id.name
        p0.service?.text = dentalAppointmentsList[p1].services_id.name
        p0.cancelButton.setOnClickListener {
            Log.d("Click cancel button", "Its click")
            MaterialAlertDialogBuilder(context,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                .setTitle("Cancel appointment")
                .setMessage("Are you sure cancel this appointment")
                .setPositiveButton("Yes") { dialogInterface, i ->
                    OnlineDentalClinicAPI.cancelAppointments(
                        id = dentalAppointmentsList[p1]._id,
                        responseHandler = {
                            Log.d("Appointment has been deleted", it)
                        },
                        responseError = {
                            Log.d("Error", "Error ${it?.errorCode}: ${it?.errorBody} ${it?.localizedMessage}")
                        },
                        token = token
                    )
                    Toast.makeText(context, "Appointment have been deleted", Toast.LENGTH_LONG).show()

                    if (clickListener != null){
                        clickListener?.onClick(p1)
                    }
                }
                .setNegativeButton("No"){ dialogInterface, i ->
                    Toast.makeText(context, "Cancel operation", Toast.LENGTH_LONG).show()
                }
                .show()
        }
        p0.cardViewAppointments?.setOnClickListener {

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.textDay)
        val month: TextView = itemView.findViewById(R.id.textMonth)
        val year: TextView = itemView.findViewById(R.id.TextYear)
        val time: TextView = itemView.findViewById(R.id.textTime)
        val dentist: TextView = itemView.findViewById(R.id.txtDentist)
        val clinic: TextView = itemView.findViewById(R.id.txtClinic)
        val service: TextView = itemView.findViewById(R.id.txtService)
        val cardViewAppointments: CardView = itemView.findViewById(R.id.card_appointments)
        val cancelButton: Button = itemView.findViewById(R.id.cancel_button)
        val editButton: Button = itemView.findViewById(R.id.edit_button)
    }
}