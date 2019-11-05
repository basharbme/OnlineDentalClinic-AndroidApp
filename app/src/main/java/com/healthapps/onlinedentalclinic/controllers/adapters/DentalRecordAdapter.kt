package com.healthapps.onlinedentalclinic.controllers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.models.DentalRecords

class DentalRecordAdapter(private val dentalRecordsList: ArrayList<DentalRecords>,
                          private val listener: ClickListener) :
    RecyclerView.Adapter<DentalRecordAdapter.ViewHolder>() {

    var context: Context? = null

    open interface ClickListener {
        fun onClick(position: Int)

    }

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_record, p0, false)
        context = p0?.context
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dentalRecordsList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        clickListener = listener

        p0.patient?.text = dentalRecordsList[p1].patients_id.fullname
        p0.age?.text = dentalRecordsList[p1].age
        p0.reasonConsultation?.text = dentalRecordsList[p1].reason_consultation
        p0.signsSymptoms?.text = dentalRecordsList[p1].signs_symptoms
        p0.viewButton.setOnClickListener {

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val patient: TextView = itemView.findViewById(R.id.textPatient)
        val age: TextView = itemView.findViewById(R.id.txtAge)
        val reasonConsultation: TextView = itemView.findViewById(R.id.txtReasonConsultation)
        val signsSymptoms: TextView = itemView.findViewById(R.id.txtSignsSymptoms)
        val viewButton: Button = itemView.findViewById(R.id.view_button)
        val editButton: Button = itemView.findViewById(R.id.edit_button)
    }
}