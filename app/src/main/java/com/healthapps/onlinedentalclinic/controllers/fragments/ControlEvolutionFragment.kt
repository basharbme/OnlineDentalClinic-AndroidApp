package com.healthapps.onlinedentalclinic.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.healthapps.onlinedentalclinic.R
import kotlinx.android.synthetic.main.fragment_control_evolution.*

class ControlEvolutionFragment(private val listener: ClickListener) : Fragment() {

    interface ClickListener {
        fun onClick(save: Boolean, controlEvolution: Map<String, Any>)
    }

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control_evolution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Counter the complete fields
        var isBlankCounter = 0

        //Error that describing the empty fields
        var error = "Enter data in:"

        // Check if the fields are empty
        button_next_control_evolution.setOnClickListener {

            // Change the value of clickLister from null to listener value
            clickListener = listener

            editTextControlEvolution.text.toString().apply {
                if (!this.isBlank()){
                    isBlankCounter ++
                } else {
                    error += " Control evolution,"
                }
            }

            // Check if CreateDentalRecordActivity overrides the click method when calling this fragment
            if (clickListener != null) {
                val controlEvolution =
                    mapOf("control_evolution" to editTextControlEvolution.text.toString())

                // If the fields are not empty, return click true and controlEvolution object
                if (isBlankCounter == 1) {
                    isBlankCounter = 0
                    clickListener?.onClick(true, controlEvolution)
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

                    // If the fields are empty, return click false and controlEvolution object
                    clickListener?.onClick(false, controlEvolution)
                }
            }
        }
    }
}
