package com.healthapps.onlinedentalclinic.controllers.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.fragments.*
import com.healthapps.onlinedentalclinic.models.DentalRecords
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import kotlinx.android.synthetic.main.activity_create_dental_record.*
import java.lang.Exception

class CreateDentalRecordActivity : AppCompatActivity() {

    var anamnesis = mapOf<String, Any>()
    var reasonConsultation = mapOf<String, Any>()
    var currentIllness = mapOf<String, Any>()
    var background = mapOf<String, Any>()
    var clinicExamination = mapOf<String, Any>()
    var diagnosis = mapOf<String, Any>()
    var treatmentPlan = mapOf<String, Any>()
    var controlEvolution = mapOf<String, Any>()
    var patientDischarge = mapOf<String, Any>()
    var dentalRecords: DentalRecords = DentalRecords()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dental_record)

        val anamnesisFragment = AnamnesisFragment(object : AnamnesisFragment.ClickListener {
            override fun onClick(save: Boolean, anamnesis: Map<String, Any>) {
                if (save) {
                    this@CreateDentalRecordActivity.anamnesis = anamnesis
                    Log.d("Anamnsis", this@CreateDentalRecordActivity.anamnesis.toString())
                    textView_anamnasis.setTextColor(Color.BLUE)
                    viewOptions(true, View.VISIBLE)
                }
            }
        })

        val medicalConsultationFragment =
            MedicalConsultationFragment(object : MedicalConsultationFragment.ClickListener {
                override fun onClick(save: Boolean, medicalConsultation: Map<String, Any>) {
                    if (save) {
                        this@CreateDentalRecordActivity.reasonConsultation = medicalConsultation
                        Log.d(
                            "Reason consultation",
                            this@CreateDentalRecordActivity.reasonConsultation.toString()
                        )
                        textView_medical_consultation.setTextColor(Color.BLUE)
                        viewOptions(true, View.VISIBLE)
                    }
                }
            })

        val currentIllnessFragment =
            CurrentIllnessFragment(object : CurrentIllnessFragment.ClickListener {
                override fun onClick(save: Boolean, currentIllness: Map<String, Any>) {
                    if (save) {
                        this@CreateDentalRecordActivity.currentIllness = currentIllness
                        Log.d(
                            "Current illness",
                            this@CreateDentalRecordActivity.currentIllness.toString()
                        )
                        textView_current_illness.setTextColor(Color.BLUE)
                        viewOptions(true, View.VISIBLE)
                    }
                }
            })

        val backgroundFragment = BackgroundFragment(object : BackgroundFragment.ClickListener {
            override fun onClick(save: Boolean, background: Map<String, Any>) {
                if (save) {
                    this@CreateDentalRecordActivity.background = background
                    Log.d("Background", this@CreateDentalRecordActivity.background.toString())
                    textView_background.setTextColor(Color.BLUE)
                    viewOptions(true, View.VISIBLE)
                }
            }
        })

        val clinicExaminationFragment =
            ClinicalExaminationFragment(object : ClinicalExaminationFragment.ClickListener {
                override fun onClick(save: Boolean, clinicExamination: Map<String, Any>) {
                    if (save) {
                        this@CreateDentalRecordActivity.clinicExamination = clinicExamination
                        Log.d(
                            "Clinic examination",
                            this@CreateDentalRecordActivity.clinicExamination.toString()
                        )
                        textView_clinic_examination.setTextColor(Color.BLUE)
                        viewOptions(true, View.VISIBLE)
                    }
                }
            })

        val diagnosisFragment = DiagnosisFragment(object : DiagnosisFragment.ClickListener {
            override fun onClick(save: Boolean, diagnosis: Map<String, Any>) {
                if (save) {
                    this@CreateDentalRecordActivity.diagnosis = diagnosis
                    Log.d("Diagnosis", this@CreateDentalRecordActivity.diagnosis.toString())
                    textView_diagnosis.setTextColor(Color.BLUE)
                    viewOptions(true, View.VISIBLE)
                }
            }
        })

        val treatmentPlanFragment =
            TreatmentPlanFragment(object : TreatmentPlanFragment.ClickListener {
                override fun onClick(save: Boolean, treatmentPlan: Map<String, Any>) {
                    if (save) {
                        this@CreateDentalRecordActivity.treatmentPlan = treatmentPlan
                        Log.d(
                            "Treatment plan",
                            this@CreateDentalRecordActivity.treatmentPlan.toString()
                        )
                        textView_treatment_plan.setTextColor(Color.BLUE)
                        viewOptions(true, View.VISIBLE)
                    }
                }
            })

        val controlEvolutionFragment =
            ControlEvolutionFragment(object : ControlEvolutionFragment.ClickListener {
                override fun onClick(save: Boolean, controlEvolution: Map<String, Any>) {
                    if (save) {
                        this@CreateDentalRecordActivity.controlEvolution = controlEvolution
                        Log.d(
                            "Control and evolution",
                            this@CreateDentalRecordActivity.controlEvolution.toString()
                        )
                        textView_control_evolution.setTextColor(Color.BLUE)
                        viewOptions(true, View.VISIBLE)
                    }
                }
            })

        val patientDischargeFragment =
            PatientDischargeFragment(object : PatientDischargeFragment.ClickListener {
                override fun onClick(save: Boolean, patientDischarge: Map<String, Any>) {
                    if (save) {

                        try {
                            this@CreateDentalRecordActivity.patientDischarge = patientDischarge
                            Log.d("Patient discharge", patientDischarge.toString())
                            dentalRecords.dentists_id = MainActivity.currentUser
                            dentalRecords.patients_id = anamnesis["patient"] as Person
                            dentalRecords.birthplace = anamnesis["birthplace"] as String
                            dentalRecords.birthday = anamnesis["birthday"] as String
                            dentalRecords.travels = anamnesis["travels"] as String
                            dentalRecords.phone = anamnesis["phone"] as String
                            dentalRecords.reason_consultation =
                                reasonConsultation["reason_consultation"] as String
                            dentalRecords.sick_time = currentIllness["sick_time"] as String
                            dentalRecords.signs_symptoms = currentIllness["signs_symptoms"] as String
                            dentalRecords.biological_functions =
                                currentIllness["biological_functions"] as String
                            dentalRecords.personal_history = background["personal_history"] as String
                            dentalRecords.family_background = background["background_family"] as String
                            dentalRecords.vital_signs = clinicExamination["vital_signs"] as String
                            dentalRecords.general_exam_data =
                                clinicExamination["general_clinic_examination"] as String
                            dentalRecords.odontostomatological_exam =
                                clinicExamination["odontostomatological_exam"] as String
                            dentalRecords.presumptive_diagnosis =
                                diagnosis["presumptive_diagnosis"] as String
                            dentalRecords.treatment_plan = treatmentPlan["treatment_plan"] as String
                            dentalRecords.control_evolution =
                                controlEvolution["control_evolution"] as String
                            dentalRecords.patient_discharge =
                                patientDischarge["patient_discharge"] as String
                        }catch (e: Exception) {
                            print(e.localizedMessage)
                        }

                        OnlineDentalClinicAPI.saveDentalRecords(
                            dentalRecords = dentalRecords,
                            responseHandler = {
                                Log.d("Dental records", it.toString())
                            },
                            responseError = {
                                Log.d(
                                    "Error",
                                    "Error $it.errorCode: $it.errorBody $it.localizedMessage"
                                )
                            },
                            token = getString(R.string.token)
                        )
                        val intentResult = Intent()

                        intentResult.putExtra("dentalRecords", dentalRecords)
                        setResult(Activity.RESULT_OK, intentResult)

                        textView_patient_discharge.setTextColor(Color.BLUE)
                        viewOptions(true, View.VISIBLE)
                        finish()
                    }
                }
            })

        textView_anamnasis.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment__create_dental_Records, anamnesisFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_medical_consultation.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fragment__create_dental_Records,
                medicalConsultationFragment
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_current_illness.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fragment__create_dental_Records,
                currentIllnessFragment
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_background.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment__create_dental_Records, backgroundFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_clinic_examination.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fragment__create_dental_Records,
                clinicExaminationFragment
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_diagnosis.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment__create_dental_Records, diagnosisFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_treatment_plan.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment__create_dental_Records, treatmentPlanFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_control_evolution.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fragment__create_dental_Records,
                controlEvolutionFragment
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }

        textView_patient_discharge.setOnClickListener {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(
                R.id.fragment__create_dental_Records,
                patientDischargeFragment
            )
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            viewOptions(false, View.INVISIBLE)
        }
    }

    private fun viewOptions(click: Boolean, visibility: Int) {
        textView_anamnasis.isClickable = click
        textView_anamnasis.visibility = visibility
        textView_current_illness.isClickable = click
        textView_current_illness.visibility = visibility
        textView_background.isClickable = click
        textView_background.visibility = visibility
        textView_medical_consultation.isClickable = click
        textView_medical_consultation.visibility = visibility
        textView_clinic_examination.isClickable = click
        textView_clinic_examination.visibility = visibility
        textView_control_evolution.isClickable = click
        textView_control_evolution.visibility = visibility
        textView_diagnosis.isClickable = click
        textView_diagnosis.visibility = visibility
        textView_treatment_plan.isClickable = click
        textView_treatment_plan.visibility = visibility
        textView_patient_discharge.isClickable = click
        textView_patient_discharge.visibility = visibility
        divider_.visibility = visibility
        divider_6.visibility = visibility
        divider_7.visibility = visibility
        divider_8.visibility = visibility
        divider_9.visibility = visibility
        divider_10.visibility = visibility
        divider_11.visibility = visibility
        divider_12.visibility = visibility
        divider_13.visibility = visibility
    }

    override fun onBackPressed() {
        super.onBackPressed()

        viewOptions(true, View.VISIBLE)
    }
}
