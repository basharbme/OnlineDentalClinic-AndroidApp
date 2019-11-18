package com.healthapps.onlinedentalclinic.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.models.Odontogram

class OdontogramAdapter(private val odontogramList: ArrayList<Odontogram>, private val listener: ClickLister) :
    RecyclerView.Adapter<OdontogramAdapter.ViewHolder>(){

    interface ClickLister{
        fun onClick(position: Int)
    }

    companion object {
        var clickListener: ClickLister? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_odontogram, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return odontogramList.size
    }

    override fun onBindViewHolder(parent: ViewHolder, position: Int) {
        clickListener = listener

        parent.name.text = odontogramList[position].patients_id.fullname
        parent.age.text = odontogramList[position].patients_id.age.toString()
        parent.createDentalPiece.setOnClickListener {
            if(clickListener != null){
                clickListener?.onClick(position)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.textViewPatient)
        val age: TextView = itemView.findViewById(R.id.textViewAge)
        val createDentalPiece: Button = itemView.findViewById(R.id.create_button)
    }
}