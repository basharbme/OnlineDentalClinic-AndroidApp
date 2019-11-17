package com.healthapps.onlinedentalclinic.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.models.DentalPiece
import com.healthapps.onlinedentalclinic.models.DentalPieceDataModel
import com.healthapps.onlinedentalclinic.models.Person

class DentalPieceAdapter(
    private val dentalPieceList: ArrayList<DentalPiece>,
    private val dentalPieceListDataModel: ArrayList<DentalPieceDataModel>,
    private val listener: ClickLister
) :
    RecyclerView.Adapter<DentalPieceAdapter.ViewHolder>() {

    interface ClickLister {
        fun onClick(position: Int)
    }

    companion object {
        var clickListener: ClickLister? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_dental_piece, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dentalPieceListDataModel.size
    }

    override fun onBindViewHolder(parent: ViewHolder, position: Int) {
        clickListener = listener

        parent.glideLoad.load(dentalPieceListDataModel[position].imageViewCode!!)
            .into(parent.dentalPiece)
        parent.code.text = dentalPieceListDataModel[position].number
        parent.description.text = dentalPieceListDataModel[position].description
        parent.cardViewDentalPiece.setOnClickListener {
            if (clickListener != null) {
                clickListener?.onClick(position)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val glideLoad = Glide.with(itemView.context)
        val dentalPiece: ImageView = itemView.findViewById(R.id.imageView_dental_piece)
        val code: TextView = itemView.findViewById(R.id.textView_code)
        val description: TextView = itemView.findViewById(R.id.textView_description)
        val cardViewDentalPiece: CardView = itemView.findViewById(R.id.card_dental_piece)
    }
}