package com.healthapps.onlinedentalclinic.models

import org.json.JSONObject
import java.io.Serializable

data class DentalPiece (
    var _id: String,
    var number: String,
    var description: String,
    var odontograms_id: Odontogram
): Serializable {
    constructor(): this (
        "",
        "",
        "",
        Odontogram()
    )

    fun convertToJson(): JSONObject {
        val jsonDentalPiece = JSONObject()
        val jsonOdontogram = odontograms_id.convertToJson()

        jsonDentalPiece.put("_id", _id)
        jsonDentalPiece.put("number", number)
        jsonDentalPiece.put("description", description)
        jsonDentalPiece.put("odontograms_id", jsonOdontogram)

        return jsonDentalPiece
    }
}