package com.healthapps.onlinedentalclinic.controllers.models

import org.json.JSONObject
import java.io.Serializable

data class Person(
    var id: Int,
    var fullname: String,
    var age: Int,
    var email: String,
    var username: String,
    var password: String,
    var roleId: Role
): Serializable{
    constructor(): this(
        0,
        "",
        0,
        "",
        "",
        "",
        Role()
    )
    fun convertToJson(): JSONObject{
        val jsonPerson = JSONObject()
        val jsonRole = roleId.convertToJson()

        jsonPerson.put("id", id)
        jsonPerson.put("fullname", fullname)
        jsonPerson.put("age", age)
        jsonPerson.put("email", email)
        jsonPerson.put("username", username)
        jsonPerson.put("password", password)
        jsonPerson.put("roleId", jsonRole)

        return jsonPerson
    }
}