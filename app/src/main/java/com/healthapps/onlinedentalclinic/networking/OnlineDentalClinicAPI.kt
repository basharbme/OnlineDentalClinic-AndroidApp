package com.healthapps.onlinedentalclinic.networking


import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.ParsedRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import com.healthapps.onlinedentalclinic.models.*
import org.json.JSONObject


class OnlineDentalClinicAPI {
    companion object {
        private val BASE_URL = "http://184.172.214.131:31380/api-gateway-mobile"
        private val securityURL = "$BASE_URL/authorizations"
        private val peopleURL = "$BASE_URL/people"
        private val relationshipsURL = "$BASE_URL/relationships"
        private val dentalAppointmentsURL = "$BASE_URL/dental-appointments"
        private val dentalRecordsURL = "$BASE_URL/dental-records"
        private val odontogramsURL = "$BASE_URL/odontograms"
        private val dentalPiecesURL = "$BASE_URL/dental-pieces"
        private val clinicsURL = "$BASE_URL/clinics"
        private val employeesURL = "$BASE_URL/employees"
        private val servicesURL = "$BASE_URL/services"
        private val salesURL = "$BASE_URL/sales"
        private val schedules = "$BASE_URL/schedules"
        private const val TAG = "OnlineDentalClinicApi"

        //Login
        fun login(
            person: Person, responseHandler: (JSONObject?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            post(
                person.convertToJson(),
                "$peopleURL/login",
                responseHandler,
                responseError,
                token
            )
        }

        //Get-All
        fun getDentalAppointments(
            responseHandler: (ArrayList<DentalAppointment>?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            get(
                dentalAppointmentsURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Get-All
        fun getOdontograms(
            responseHandler: (ArrayList<Odontogram>?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            get(
                odontogramsURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Get-All
        fun getDentalRecords(
            responseHandler: (ArrayList<DentalRecords>?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            get(
                dentalRecordsURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Get-All
        fun getClinics(
            responseHandler: (ArrayList<Clinic>?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            get(
                clinicsURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Get-All
        fun getPeople(
            responseHandler: (ArrayList<Person>?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            get(
                peopleURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Get-All
        fun getService(
            responseHandler: (ArrayList<Service>?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            get(
                servicesURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Get-Dental piece by odontogram
        fun getDentalPieceByOdontogram(
            odontogram: Odontogram, responseHandler: (ArrayList<DentalPiece>?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            getByModel(
                odontogram.convertToJson(),
                "$dentalPiecesURL/odontogram",
                responseHandler,
                responseError,
                token
            )
        }

        //Save-Appointments
        fun saveAppointments(
            dentalAppointments: DentalAppointment, responseHandler: (JSONObject?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            post(
                dentalAppointments.convertToJson(),
                dentalAppointmentsURL,
                responseHandler,
                responseError,
                token
            )
        }

        fun saveDentalRecords(
            dentalRecords: DentalRecords, responseHandler: (JSONObject?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            post(
                dentalRecords.convertToJson(),
                dentalRecordsURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Save-Odontograms
        fun saveOdontograms(
            odontograms: Odontogram, responseHandler: (JSONObject?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            post(
                odontograms.convertToJson(),
                odontogramsURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Save-Dental piece
        fun saveDentalPiece(
            dentalPiece: DentalPiece, responseHandler: (JSONObject?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            post(
                dentalPiece.convertToJson(),
                dentalPiecesURL,
                responseHandler,
                responseError,
                token
            )
        }

        //Delete-Appointments
        fun cancelAppointments(
            id: String, responseHandler: (String?) -> Unit,
            responseError: (ANError?) -> Unit, token: String
        ) {
            val urlDelete = "$dentalAppointmentsURL/$id"

            delete(
                urlDelete,
                responseHandler,
                responseError,
                token
            )
        }

        //Post-All
        private inline fun post(
            jsonObj: JSONObject, url: String,
            crossinline responseHandler: (JSONObject?) -> Unit,
            crossinline responseError: (ANError?) -> Unit, token: String
        ) {
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

        //Get by model
        private inline fun <reified T> getByModel(
            jsonObj: JSONObject, url: String,
            crossinline responseHandler: (ArrayList<T>?) -> Unit,
            crossinline responseError: (ANError?) -> Unit, token: String
        ) {
            AndroidNetworking.post(url)
                .addHeaders("Authorization", "Bearer $token")
                .addHeaders("Content-Type", "application/json")
                .addJSONObjectBody(jsonObj)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObjectList(
                    T::class.java,
                    object : ParsedRequestListener<ArrayList<T>> {
                        override fun onResponse(response: ArrayList<T>) {
                            responseHandler(response)
                            Log.d("Dental pieces", response.toString())
                        }

                        override fun onError(anError: ANError?) {
                            responseError(anError)
                        }
                    }
                )
        }

        //Get-All
        private inline fun <reified T> get(
            url: String, crossinline responseHandler: (ArrayList<T>?) -> Unit,
            crossinline responseError: (ANError?) -> Unit, token: String
        ) {
            AndroidNetworking.get(url)
                .addHeaders("Authorization", "Bearer $token")
                .setTag(TAG)
                .setPriority(Priority.HIGH)
                .build()
                .getAsObjectList(
                    T::class.java,
                    object : ParsedRequestListener<ArrayList<T>> {
                        override fun onResponse(response: ArrayList<T>) {
                            responseHandler(response)
                            Log.d("clinics", response.toString())
                        }

                        override fun onError(anError: ANError?) {
                            responseError(anError)
                        }
                    }
                )
        }

        private inline fun delete(
            url: String, crossinline responseHandler: (String?) -> Unit,
            crossinline responseError: (ANError?) -> Unit, token: String
        ) {
            AndroidNetworking.delete(url)
                .addHeaders("Authorization", "Bearer $token")
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(
                    object : StringRequestListener {
                        override fun onResponse(response: String?) {
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