package com.healthapps.onlinedentalclinic.controllers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.DentalPieceAdapter
import com.healthapps.onlinedentalclinic.models.DentalPiece
import com.healthapps.onlinedentalclinic.models.DentalPieceDataModel
import com.healthapps.onlinedentalclinic.utils.GridItemDecoration
import kotlinx.android.synthetic.main.activity_create_dental_piece.*

class CreateDentalPieceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dental_piece)

        initView()
    }

    private fun initView() {
        RV_dental_piece.layoutManager = GridLayoutManager(this, 2)

        RV_dental_piece.addItemDecoration(GridItemDecoration(10, 2))

        val dentalPieceList = ArrayList<DentalPiece>()
        val dentalPieceListModel = generateDentalPiece()
        val adapter = DentalPieceAdapter(
            dentalPieceList,
            dentalPieceListModel,
            object : DentalPieceAdapter.ClickLister {
                override fun onClick(position: Int) {

                }
            })

        RV_dental_piece.adapter = adapter
    }

    private fun generateDentalPiece(): ArrayList<DentalPieceDataModel> {
        val listOfDentalPieceModel = ArrayList<DentalPieceDataModel>()

        var dentalPieceModel = DentalPieceDataModel("no code", "No description", R.drawable.piece_1)
        listOfDentalPieceModel.add(dentalPieceModel)

        dentalPieceModel = DentalPieceDataModel("No code", "No description", R.drawable.piece_2)
        listOfDentalPieceModel.add(dentalPieceModel)

        dentalPieceModel = DentalPieceDataModel("No code", "No description", R.drawable.piece_3)
        listOfDentalPieceModel.add(dentalPieceModel)

        dentalPieceModel = DentalPieceDataModel("No code", "No description", R.drawable.piece_4)
        listOfDentalPieceModel.add(dentalPieceModel)

        dentalPieceModel = DentalPieceDataModel("No code", "No description", R.drawable.piece_5)
        listOfDentalPieceModel.add(dentalPieceModel)


        return listOfDentalPieceModel
    }
}
