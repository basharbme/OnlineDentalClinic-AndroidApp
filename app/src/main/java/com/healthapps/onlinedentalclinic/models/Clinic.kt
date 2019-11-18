package com.healthapps.onlinedentalclinic.models

import org.json.JSONObject
import java.io.Serializable

data class Clinic(
    var _id: String,
    var name: String,
    var description: String,
    var address: String,
    var attention_hours: String,
    var specialties: String
): Serializable{
    constructor(): this(
        "",
        "",
        "",
        "",
        "",
        ""
    )
    fun convertToJson(): JSONObject{
        val jsonClinics = JSONObject()

        jsonClinics.put("_id", _id)
        jsonClinics.put("name", name)
        jsonClinics.put("description", description)
        jsonClinics.put("address", address)
        jsonClinics.put("attention_hours", attention_hours)
        jsonClinics.put("specialties", specialties)

        return jsonClinics
    }
}