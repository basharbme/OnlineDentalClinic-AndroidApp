package com.healthapps.onlinedentalclinic.controllers.models

import org.json.JSONObject
import java.io.Serializable

data class Role(
    var id: Int,
    var name: String,
    var description: String
): Serializable{
    constructor(): this(
        0,
        "",
        ""
    )
    fun convertToJson(): JSONObject{
        val jsonRole = JSONObject()

        jsonRole.put("_id", id)
        jsonRole.put("name", name)
        jsonRole.put("description", description)

        return jsonRole
    }
}