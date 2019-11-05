package com.healthapps.onlinedentalclinic.controllers.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.adapters.ViewPagerAdapter
import com.healthapps.onlinedentalclinic.controllers.fragments.*
import com.healthapps.onlinedentalclinic.models.DentalRecords
import com.healthapps.onlinedentalclinic.models.Person
import com.healthapps.onlinedentalclinic.networking.OnlineDentalClinicAPI
import com.healthapps.onlinedentalclinic.utils.SinglentonOnClick
import kotlinx.android.synthetic.main.activity_create_dental_record.*

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

        val adapter = ViewPagerAdapter(supportFragmentManager)

        val anamnesisFragment = AnamnesisFragment(object : AnamnesisFragment.ClickListener {
            override fun onClick(save: Boolean, anamnesis: Map<String, Any>) {
                if (save) {
                    this@CreateDentalRecordActivity.anamnesis = anamnesis
                    Log.d("Anamnsis", this@CreateDentalRecordActivity.anamnesis.toString())
                    enableTab(tabs, 1)
                    viewPager.currentItem = 1
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
                        enableTab(tabs, 2)
                        viewPager.currentItem = 2
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
                        enableTab(tabs, 3)
                        viewPager.currentItem = 3
                    }
                }
            })

        val backgroundFragment = BackgroundFragment(object : BackgroundFragment.ClickListener {
            override fun onClick(save: Boolean, background: Map<String, Any>) {
                if (save) {
                    this@CreateDentalRecordActivity.background = background
                    Log.d("Background", this@CreateDentalRecordActivity.background.toString())
                    enableTab(tabs, 4)
                    viewPager.currentItem = 4
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
                        enableTab(tabs, 5)
                        viewPager.currentItem = 5
                    }
                }
            })

        val diagnosisFragment = DiagnosisFragment(object : DiagnosisFragment.ClickListener {
            override fun onClick(save: Boolean, diagnosis: Map<String, Any>) {
                if (save) {
                    this@CreateDentalRecordActivity.diagnosis = diagnosis
                    Log.d("Diagnosis", this@CreateDentalRecordActivity.diagnosis.toString())
                    enableTab(tabs, 6)
                    viewPager.currentItem = 6
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
                        enableTab(tabs, 7)
                        viewPager.currentItem = 7
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
                        enableTab(tabs, 8)
                        viewPager.currentItem = 8
                    }
                }
            })

        val patientDischargeFragment =
            PatientDischargeFragment(object : PatientDischargeFragment.ClickListener {
                override fun onClick(save: Boolean, patientDischarge: Map<String, Any>) {
                    if (save) {
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

                        finish()
                    }
                }
            })

        adapter.addFragment(anamnesisFragment, "Anamnasis")
        adapter.addFragment(medicalConsultationFragment, "Medical consultation")
        adapter.addFragment(currentIllnessFragment, "Current illness")
        adapter.addFragment(backgroundFragment, "Background")
        adapter.addFragment(clinicExaminationFragment, "Clinic examination")
        adapter.addFragment(diagnosisFragment, "Diagnosis")
        adapter.addFragment(treatmentPlanFragment, "Treatment plan")
        adapter.addFragment(controlEvolutionFragment, "Control and evolution")
        adapter.addFragment(patientDischargeFragment, "Patient discharge")

        viewPager.adapter = adapter
        viewPager.beginFakeDrag()
        tabs.setupWithViewPager(viewPager)

        disableTab(tabs, 1)
        disableTab(tabs, 2)
        disableTab(tabs, 3)
        disableTab(tabs, 4)
        disableTab(tabs, 5)
        disableTab(tabs, 6)
        disableTab(tabs, 7)
        disableTab(tabs, 8)
    }

    private fun enableTab(tabLayout: TabLayout, index: Int) {
        (tabLayout.getChildAt(0) as ViewGroup).getChildAt(index).isEnabled = true
        (tabLayout.getChildAt(0) as ViewGroup).getChildAt(index).alpha = 1.0F
        (tabLayout.getChildAt(0) as ViewGroup).getChildAt(index).isClickable = true
    }

    private fun disableTab(tabLayout: TabLayout, index: Int) {
        (tabLayout.getChildAt(0) as ViewGroup).getChildAt(index).isEnabled = false
        (tabLayout.getChildAt(0) as ViewGroup).getChildAt(index).alpha = 0.5F
        (tabLayout.getChildAt(0) as ViewGroup).getChildAt(index).isClickable = false
    }
}
