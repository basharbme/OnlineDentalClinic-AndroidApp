package com.healthapps.onlinedentalclinic.controllers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.healthapps.onlinedentalclinic.R
import kotlinx.android.synthetic.main.fragment_background.*

class BackgroundFragment(private val listener: ClickListener) : Fragment() {

    interface ClickListener {
        fun onClick(save: Boolean, background: Map<String, Any>)
    }

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_background, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Counter the complete fields
        var isBlankCounter = 0

        //Error that describing the empty fields
        var error = "Enter data in:"

        // Check if the fields are empty
        button_next_background.setOnClickListener {

            // Change the value of clickLister from null to listener value
            clickListener = listener

            editTextPersonalHistory.text.toString().apply {
                if (!this.isBlank()){
                    isBlankCounter ++
                } else {
                    error += " Personal history,"
                }
            }

            editTextFamilyBackground.text.toString().apply {
                if (!this.isBlank()){
                    isBlankCounter ++
                } else {
                    error += " Family background,"
                }
            }

            // Check if CreateDentalRecordActivity overrides the click method when calling this fragment
            if (clickListener != null) {
                val background =
                    mapOf(
                        "personal_history" to editTextPersonalHistory.text.toString(),
                        "background_family" to editTextFamilyBackground.text.toString()
                    )

                // If the fields are not empty, return click true and background object
                if (isBlankCounter == 2) {
                    isBlankCounter = 0
                    clickListener?.onClick(true, background)

                    val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()

                    fragmentTransaction.remove(this)
                    fragmentTransaction.commit()
                    fragmentManager!!.popBackStack()
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

                    // If the fields are empty, return click false and background object
                    clickListener?.onClick(false, background)
                }
            }
        }
    }
}
