package com.healthapps.onlinedentalclinic.controllers.models

import org.json.JSONObject
import java.io.Serializable

data class DentalAppointment (
    val id: String,
    val patients_id: Person,
    val dentists_id: Person,
    val clinics_id: Clinic,
    val services_id: Service,
    val date: String,
    val hour: String
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

        jsonDentalAppointment.put("id", id)
        jsonDentalAppointment.put("patients_id", jsonPatients)
        jsonDentalAppointment.put("dentists_id", jsonDentist)
        jsonDentalAppointment.put("clinics_id", jsonClinics)
        jsonDentalAppointment.put("services_id", jsonService)
        jsonDentalAppointment.put("date", date)
        jsonDentalAppointment.put("hour", hour)

        return jsonDentalAppointment
    }
}