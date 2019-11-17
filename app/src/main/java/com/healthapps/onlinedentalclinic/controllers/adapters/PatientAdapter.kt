package com.healthapps.onlinedentalclinic.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.models.Person

class PatientAdapter(private val peopleList: ArrayList<Person>, private val listener: ClickLister) :
    RecyclerView.Adapter<PatientAdapter.ViewHolder>(){

    interface ClickLister{
        fun onClick(position: Int)
    }

    companion object {
        var clickListener: ClickLister? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_patient, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(parent: ViewHolder, position: Int) {
        clickListener = listener

        parent.name.text = peopleList[position].fullname
        parent.age.text = peopleList[position].age.toString()
        parent.cardViewPatient.setOnClickListener {
            if(clickListener != null){
                clickListener?.onClick(position)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.textViewPatient)
        val age: TextView = itemView.findViewById(R.id.textViewAge)
        val cardViewPatient: CardView = itemView.findViewById(R.id.card_patient)
    }
}