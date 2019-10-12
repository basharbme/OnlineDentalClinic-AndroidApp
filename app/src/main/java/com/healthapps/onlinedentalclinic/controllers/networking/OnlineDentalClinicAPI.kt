package com.healthapps.onlinedentalclinic.controllers.networking

import com.healthapps.onlinedentalclinic.controllers.models.Person
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import org.json.JSONObject

class OnlineDentalClinicAPI{
    companion object{
        private val BASE_URL = "http://184.172.214.131:31380/api-gateway-mobile"
        private val securityURL = "$BASE_URL/authorizations"
        private val peopleURL = "$BASE_URL/people"
        private val relationshipsURL = "$BASE_URL/relationships"
        private val dentalAppointmentsURL = "$BASE_URL/dental-appointments"
        private val dentalRecordsURL = "$BASE_URL/dental-records"
        private val odontogramsURL = "$BASE_URL/odontograms"
        private val clinicsURL = "$BASE_URL/clinics"
        private val employeesURL = "$BASE_URL/employees"
        private val servicesURL = "$BASE_URL/services"
        private val salesURL = "$BASE_URL/sales"
        private val schedules = "$BASE_URL/schedules"
        private val TAG = "OnlineDentalClinicApi"

        //Login
        fun login(person: Person, responseHandler: (JSONObject?) -> Unit,
                  responseError: (ANError?) -> Unit, token: String){
            post(person.convertToJson(), "$peopleURL/login", responseHandler, responseError, token)
        }

        //Post-all
        private inline fun post(jsonObj: JSONObject, url: String,
                                crossinline responseHandler: (JSONObject?) -> Unit,
                                crossinline responseError: (ANError?) -> Unit, token: String){
            AndroidNetworking.post(url)
                .addHeaders("Authorization", "Bearer $token")
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(jsonObj)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(
                    object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            responseError(anError)
                        }
                    }
                )
        }
    }
}