package com.healthapps.onlinedentalclinic.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.models.DentalAppointment

class AppointmentAdapter(private val dentalAppointmentsList: ArrayList<DentalAppointment>) :
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_appointment, p0, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return dentalAppointmentsList.size
    }
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val date = dentalAppointmentsList[p1].date
        val partsDate = date.split(Regex("/"))

        p0.day?.text = partsDate[0]
        p0.month?.text = partsDate[1]
        p0.year?.text = partsDate[2]
        p0.time?.text = dentalAppointmentsList[p1].hour
        p0.dentist?.text = dentalAppointmentsList[p1].dentists_id.fullname
        p0.clinic?.text = dentalAppointmentsList[p1].clinics_id.name
        p0.service?.text = dentalAppointmentsList[p1].services_id.name
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day = itemView.findViewById<TextView>(R.id.textDay)
        val month = itemView.findViewById<TextView>(R.id.textMonth)
        val year = itemView.findViewById<TextView>(R.id.TextYear)
        val time = itemView.findViewById<TextView>(R.id.textTime)
        val dentist = itemView.findViewById<TextView>(R.id.textDentist)
        val clinic = itemView.findViewById<TextView>(R.id.textClinic)
        val service = itemView.findViewById<TextView>(R.id.textService)
    }
}