package com.healthapps.onlinedentalclinic.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.models.Service

class ServiceAdapter(private val servicesList: ArrayList<Service>, private val listener: ClickListener) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>(){

    open interface ClickListener{
        fun onClick(position: Int)
    }

    companion object{
        var clickListener: ClickListener? = null
    }

    override fun getItemCount(): Int {
        return servicesList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_service, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(parent: ViewHolder, position: Int) {
        clickListener = listener

        parent.name.text = servicesList[position].name
        parent.description.text = servicesList[position].description
        parent.cost.text = servicesList[position].cost
        parent.cardViewService.setOnClickListener {
            if(clickListener != null)
                clickListener?.onClick(position)

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.textService)
        val description: TextView = itemView.findViewById(R.id.textDescription)
        val cost: TextView = itemView.findViewById(R.id.textCost)
        val cardViewService: CardView = itemView.findViewById(R.id.card_services)
    }
}