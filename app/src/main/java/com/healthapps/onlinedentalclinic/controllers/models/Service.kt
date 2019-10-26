package com.healthapps.onlinedentalclinic.controllers.models

import org.json.JSONObject
import java.io.Serializable

data class Service(
    var id: String,
    var name: String,
    var description: String,
    var cost: String
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