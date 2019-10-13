package com.healthapps.onlinedentalclinic.controllers.models

import org.json.JSONObject
import java.io.Serializable

data class Service(
    val id: String,
    val name: String,
    val description: String,
    val cost: String
): Serializable{
    constructor(): this(
        "",
        "",
        "",
        ""
    )
    fun convertToJson(): JSONObject{
        val jsonService = JSONObject()

        jsonService.put("id", id)
        jsonService.put("name", name)
        jsonService.put("description", description)
        jsonService.put("cost", cost)

        return jsonService
    }
}