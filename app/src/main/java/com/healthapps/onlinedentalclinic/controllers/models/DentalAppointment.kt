package com.healthapps.onlinedentalclinic.controllers.models

import org.json.JSONObject
import java.io.Serializable

data class DentalAppointment (
    var _id: String,
    var patients_id: Person,
    var dentists_id: Person,
    var clinics_id: Clinic,
    var services_id: Service,
    var date: String,
    var hour: String
    ): Serializable {
    constructor() : this (
        "",
        Person(),
        Person(),
        Clinic(),
        Service(),
        "",
        ""
    )
    fun convertToJson(): JSONObject{
        val jsonDentalAppointment = JSONObject()
        val jsonPatients = patients_id.convertToJson()
        val jsonDentist = dentists_id.convertToJson()
        val jsonClinics = clinics_id.convertToJson()
        val jsonService = services_id.convertToJson()

        jsonDentalAppointment.put("_id", _id)
        jsonDentalAppointment.put("patients_id", jsonPatients)
        jsonDentalAppointment.put("dentists_id", jsonDentist)
        jsonDentalAppointment.put("clinics_id", jsonClinics)
        jsonDentalAppointment.put("services_id", jsonService)
        jsonDentalAppointment.put("date", date)
        jsonDentalAppointment.put("hour", hour)

        return jsonDentalAppointment
    }
}