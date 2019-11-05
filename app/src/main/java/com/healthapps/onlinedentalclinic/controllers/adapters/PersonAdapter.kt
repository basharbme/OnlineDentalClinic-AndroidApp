package com.healthapps.onlinedentalclinic.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.models.Person

class PersonAdapter(private val peopleList: ArrayList<Person>, private val listener: ClickLister) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>(){

    open interface ClickLister{
        fun onClick(position: Int)
    }

    companion object {
        var clickListener: ClickLister? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_person, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(parent: ViewHolder, position: Int) {
        clickListener = listener

        parent.name?.text = peopleList[position].fullname
        parent.specialty?.text = peopleList[position].roleId.description
        parent.cardViewPerson.setOnClickListener {
            if(clickListener != null){
                clickListener?.onClick(position)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.textViewPatient)
        val specialty: TextView = itemView.findViewById(R.id.textSpecialty)
        val cardViewPerson: CardView = itemView.findViewById(R.id.card_people)
    }
}