package com.healthapps.onlinedentalclinic.controllers.models

import org.json.JSONObject
import java.io.Serializable

data class Clinic(
    val id: String,
    val name: String,
    val description: String,
    val address: String,
    val attention_hours: String,
    val specialties: String
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

        jsonClinics.put("id", id)
        jsonClinics.put("name", name)
        jsonClinics.put("description", description)
        jsonClinics.put("address", address)
        jsonClinics.put("attention_hours", attention_hours)
        jsonClinics.put("specialties", specialties)

        return jsonClinics
    }
}