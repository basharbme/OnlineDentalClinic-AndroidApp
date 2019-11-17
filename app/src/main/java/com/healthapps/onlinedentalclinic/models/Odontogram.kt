package com.healthapps.onlinedentalclinic.models

import org.json.JSONObject
import java.io.Serializable

data class Odontogram(
    var _id: String,
    var patients_id: Person,
    var dentists_id: Person,
    var clinics_id: Clinic
): Serializable {
    constructor(): this (
        "" ,
        Person(),
        Person(),
        Clinic()
    )

    fun convertToJson(): JSONObject {
        val jsonOdontogram = JSONObject()
        val jsonPatient = patients_id.convertToJson()
        val jsonDentist = dentists_id.convertToJson()
        val jsonClinic = clinics_id.convertToJson()

        jsonOdontogram.put("_id", _id)
        jsonOdontogram.put("patients_id", jsonPatient)
        jsonOdontogram.put("dentists_id", jsonDentist)
        jsonOdontogram.put("clinics_id", jsonClinic)

        return jsonOdontogram
    }
}