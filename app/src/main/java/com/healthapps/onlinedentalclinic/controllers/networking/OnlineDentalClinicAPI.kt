package com.healthapps.onlinedentalclinic.controllers.networking


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
    }
}