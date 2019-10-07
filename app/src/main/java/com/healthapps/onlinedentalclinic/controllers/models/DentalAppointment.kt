package com.healthapps.onlinedentalclinic.controllers.models

import java.io.Serializable

data class DentalAppointment (
    val id: String,
    val patients_id: String,
    val dentists_id: String,
    val clinics_id: String,
    val services_id: String,
    val date: String,
    val hour: String
    ): Serializable {
    constructor() : this (
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}