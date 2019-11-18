package com.healthapps.onlinedentalclinic.controllers.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.DentalPieceAdapter
import com.healthapps.onlinedentalclinic.models.DentalPiece
import com.healthapps.onlinedentalclinic.models.DentalPieceDataModel
import com.healthapps.onlinedentalclinic.models.Odontogram
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import com.healthapps.onlinedentalclinic.utils.GridItemDecoration
import kotlinx.android.synthetic.main.activity_create_dental_piece.*

class CreateDentalPieceActivity : AppCompatActivity() {
    private var dentalPieceListModel = ArrayList<DentalPieceDataModel>()
    private var position = -1
    private var odontogram = Odontogram()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dental_piece)

        odontogram = intent.extras!!.getSerializable("odontogram") as Odontogram
        dentalPieceListModel = generateDentalPiece()
    }

    private fun initView() {
        RV_dental_piece.layoutManager = GridLayoutManager(this, 2)

        RV_dental_piece.addItemDecoration(GridItemDecoration(10, 2))

        val dentalPieceList = ArrayList<DentalPiece>()
        val adapter = DentalPieceAdapter(
            dentalPieceList,
            dentalPieceListModel,
            object : DentalPieceAdapter.ClickLister {
                override fun onClick(position: Int) {
                    this@CreateDentalPieceActivity.position = position

                    val requestCode = 4
                    val intentSend = Intent(
                        this@CreateDentalPieceActivity,
                        CreateDentalPieceInformationActivity::class.java
                    )

                    intentSend.putExtra(
                        "imageViewIndex",
                        dentalPieceListModel[position].imageViewCode
                    )
                    intentSend.putExtra("number", dentalPieceListModel[position].number)

                    startActivityForResult(intentSend, requestCode)
                }
            })

        RV_dental_piece.adapter = adapter
    }

    private fun generateDentalPiece(): ArrayList<DentalPieceDataModel> {
        val listOfDentalPieceModel = ArrayList<DentalPieceDataModel>()
        var dentalPieceName = "piece"
        var dentalPieceList: ArrayList<DentalPiece>

        OnlineDentalClinicAPI.getDentalPieceByOdontogram(
            odontogram, responseHandler = {
                dentalPieceList = it as ArrayList<DentalPiece>

                var found = false

                for (x in 1..32) {
                    for (dentalPiece in dentalPieceList) {
                        if (dentalPiece.number == x.toString()) {
                            dentalPieceName += "_$x"

                            listOfDentalPieceModel.add(
                                addDentalPiece(
                                    dentalPieceName,
                                    dentalPiece.number,
                                    dentalPiece.description
                                )
                            )

                            dentalPieceName = "piece"

                            found = true

                            break
                        }
                    }

                    if (!found) {
                        dentalPieceName += "_$x"

                        listOfDentalPieceModel.add(
                            addDentalPiece(
                                dentalPieceName,
                                x.toString(),
                                "No description"
                            )
                        )

                        dentalPieceName = "piece"
                    }

                    found = false
                }

                initView()
            },
            responseError = {
                Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
            }, token = getString(R.string.token)
        )

/*        for (x in 1..32) {

            dentalPieceName += "_$x"

            listOfDentalPieceModel.add(
                addDentalPiece(
                    dentalPieceName,
                    "No code",
                    "No description"
                )
            )

            dentalPieceName = "piece"
        }*/

        return listOfDentalPieceModel
    }

    private fun addDentalPiece(
        position: String,
        code: String,
        description: String
    ): DentalPieceDataModel {
        val dentalPieceModel: DentalPieceDataModel

        val imageViewIndex = resources.getIdentifier(position, "drawable", packageName)

        dentalPieceModel = DentalPieceDataModel(code, description, imageViewIndex)

        return dentalPieceModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 4 && resultCode == Activity.RESULT_OK) {
            val dentalPieceModel = dentalPieceListModel[position]

            dentalPieceModel.number = data!!.extras!!.getString("code")!!
            dentalPieceModel.description = data.extras!!.getString("description")!!
            dentalPieceListModel[position] = dentalPieceModel

            val dentalPiece = DentalPiece()

            dentalPiece.number = dentalPieceModel.number
            dentalPiece.description = dentalPieceModel.description
            dentalPiece.odontograms_id = odontogram

            OnlineDentalClinicAPI.saveDentalPiece(dentalPiece, responseHandler = {

            }, responseError = {
                Log.d("Error", "Error $it.errorCode: $it.errorBody $it.localizedMessage")
            }, token = getString(R.string.token))

            RV_dental_piece.adapter!!.notifyItemChanged(position, dentalPieceListModel)
        }
    }
}

