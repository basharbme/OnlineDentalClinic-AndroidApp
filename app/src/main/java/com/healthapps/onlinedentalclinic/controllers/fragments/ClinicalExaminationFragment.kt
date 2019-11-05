package com.healthapps.onlinedentalclinic.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.healthapps.onlinedentalclinic.R
import kotlinx.android.synthetic.main.fragment_clinical_examination.*

class ClinicalExaminationFragment(private val listener: ClickListener) : Fragment() {

    interface ClickListener {
        fun onClick(save: Boolean, clinicExamination: Map<String, Any>)
    }

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clinical_examination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Counter the complete fields
        var isBlankCounter = 0

        //Error that describing the empty fields
        var error = "Enter data in:"

        button_next_clinical_examination.setOnClickListener {

            // Change the value of clickLister from null to listener value
            clickListener = listener

            editTextVitalSigns.text.apply {
                if (!this.isBlank()){
                    isBlankCounter ++
                } else {
                    error += "Vital signs"
                }
            }

            editTextGeneralClinicExamination.text.apply {
                if (!this.isBlank()){
                    isBlankCounter ++
                } else {
                    error += "General clinic examination"
                }
            }

            editTextOdontostomatologicalExam.text.apply {
                if (!this.isBlank()){
                    isBlankCounter ++
                } else {
                    error += "Odontostomatological exam"
                }
            }

            // Check if CreateDentalRecordActivity overrides the click method when calling this fragment
            if (clickListener != null) {
                val clinicExamination =
                    mapOf(
                        "vital_signs" to editTextVitalSigns.text.toString(),
                        "general_clinic_examination" to editTextGeneralClinicExamination.text.toString(),
                        "odontostomatological_exam" to editTextOdontostomatologicalExam.text.toString()
                    )

                // If the fields are not empty, return click true and clinicExamination object
                if (isBlankCounter == 3) {
                    isBlankCounter = 0
                    clickListener?.onClick(true, clinicExamination)
                } else {
                    isBlankCounter = 0

                    if (error != "Enter data in:") {
                        error += " to continue"

                        MaterialAlertDialogBuilder(
                            context,
                            R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered
                        )
                            .setTitle("Error: Incomplete fields")
                            .setMessage(error)
                            .setPositiveButton("OK", null)
                            .show()
                    }

                    error = "Enter data in:"

                    // If the fields are empty, return click false and clinicExamination object
                    clickListener?.onClick(false, clinicExamination)
                }
            }
        }
    }
}
