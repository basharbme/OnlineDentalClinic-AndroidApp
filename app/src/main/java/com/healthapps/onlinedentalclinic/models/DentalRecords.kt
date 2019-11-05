package com.healthapps.onlinedentalclinic.models

import org.json.JSONObject
import java.io.Serializable

data class DentalRecords(
    var _id: String,
    var age: String,
    var biological_functions: String,
    var birthday: String,
    var birthplace: String,
    var clinics_id: Clinic,
    var control_evolution: String,
    var dentists_id: Person,
    var family_background: String,
    var gender: String,
    var general_exam_data: String,
    var odontostomatological_exam: String,
    var patient_discharge: String,
    var patients_id: Person,
    var personal_history: String,
    var phone: String,
    var presumptive_diagnosis: String,
    var reason_consultation: String,
    var services_id: Service,
    var sick_time: String,
    var signs_symptoms: String,
    var travels: String,
    var treatment_plan: String,
    var vital_signs: String
) : Serializable {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        Clinic(),
        "",
        Person(),
        "",
        "",
        "",
        "",
        "",
        Person(),
        "",
        "",
        "",
        "",
        Service(),
        "",
        "",
        "",
        "",
        ""
    )

    fun convertToJson(): JSONObject {
        val jsonDentalRecords = JSONObject()
        val jsonPatients = patients_id.convertToJson()
        val jsonDentist = dentists_id.convertToJson()
        val jsonClinics = clinics_id.convertToJson()
        val jsonService = services_id.convertToJson()

        jsonDentalRecords.put("_id", _id)
        jsonDentalRecords.put("age", age)
        jsonDentalRecords.put("biological_functions", biological_functions)
        jsonDentalRecords.put("birthday", birthday)
        jsonDentalRecords.put("birthplace", birthplace)
        jsonDentalRecords.put("clinics_id", jsonClinics)
        jsonDentalRecords.put("control_evolution", control_evolution)
        jsonDentalRecords.put("dentists_id", jsonDentist)
        jsonDentalRecords.put("family_background", family_background)
        jsonDentalRecords.put("gender", gender)
        jsonDentalRecords.put("general_exam_data", general_exam_data)
        jsonDentalRecords.put("odontostomatological_exam", odontostomatological_exam)
        jsonDentalRecords.put("patient_discharge", patient_discharge)
        jsonDentalRecords.put("patients_id", jsonPatients)
        jsonDentalRecords.put("personal_history", personal_history)
        jsonDentalRecords.put("phone", phone)
        jsonDentalRecords.put("presumptive_diagnosis", presumptive_diagnosis)
        jsonDentalRecords.put("reason_consultation", reason_consultation)
        jsonDentalRecords.put("services_id", jsonService)
        jsonDentalRecords.put("sick_time", sick_time)
        jsonDentalRecords.put("signs_symptoms", signs_symptoms)
        jsonDentalRecords.put("travels", travels)
        jsonDentalRecords.put("treatment_plan", treatment_plan)
        jsonDentalRecords.put("vital_signs", vital_signs)

        return jsonDentalRecords
    }
}