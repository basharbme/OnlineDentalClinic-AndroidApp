package com.healthapps.onlinedentalclinic.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.models.Clinic


class ClinicAdapter(private val clinicList: ArrayList<Clinic>, private val listener: ClickListener) :
    RecyclerView.Adapter<ClinicAdapter.ViewHolder>() {

    open interface ClickListener {
        fun onClick(position: Int)

    }

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_clinic, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return clinicList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        clickListener = listener

        p0.nameClinic?.text = clinicList[p1].name
        p0.addressClinic?.text = clinicList[p1].address
        p0.attentionClinic?.text = clinicList[p1].attention_hours
        p0.cardViewClinic.setOnClickListener( object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if (clickListener != null)
                        clickListener?.onClick(p1)
                }
            })
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameClinic = itemView.findViewById<TextView>(R.id.textClinic)
        val addressClinic = itemView.findViewById<TextView>(R.id.textAddress)
        val attentionClinic = itemView.findViewById<TextView>(R.id.textAttention)
        val cardViewClinic = itemView.findViewById<CardView>(R.id.card_clinics)
    }
}