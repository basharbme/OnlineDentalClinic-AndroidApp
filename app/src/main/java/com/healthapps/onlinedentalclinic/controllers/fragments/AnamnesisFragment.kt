package com.healthapps.onlinedentalclinic.controllers.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.healthapps.onlinedentalclinic.R
import com.healthapps.onlinedentalclinic.controllers.activities.MainActivity
import com.healthapps.onlinedentalclinic.controllers.activities.PersonActivity
import kotlinx.android.synthetic.main.fragment_anamnesis.*
import java.util.*

class AnamnesisFragment(private val listener: ClickListener) : Fragment() {

    interface ClickListener {
        fun onClick(save: Boolean, anamnesis: Map<String, Any>)
    }

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anamnesis, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Calendar
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get((Calendar.DAY_OF_MONTH))

        // Counter the complete fields
        var isBlankCounter = 0

        // Error that describing the empty fields
        var error = "Enter data in:"

        // Call person activity for select a patient
        textViewPatient.setOnClickListener {
            val intent = Intent(view.context, PersonActivity::class.java)
            MainActivity.textViewPerson = textViewPatient

            //Send boolean variable to filter the type of user
            intent.putExtra("isPatient", true)
            activity!!.startActivity(intent)
        }

        // Picker date
        textViewBirthday.setOnClickListener {
            val dpd = DatePickerDialog(
                view.context,
                DatePickerDialog.OnDateSetListener { view, cyear, cmonth, cday ->
                    textViewBirthday.text = "$cday/$cmonth/$cyear"
                },
                year,
                month,
                day
            )

            dpd.show()
        }

        // Check if the fields are empty
        button_next_anamnesis.setOnClickListener {
            // Change the value of clickLister from null to listener value
            clickListener = listener

            textViewPatient.text.toString().apply {
                if (this != "Select an item") {
                    isBlankCounter++
                } else {
                    error += " Patient,"
                }
            }

            editTextBirthplace.text.toString().apply {
                if (!this.isBlank()) {
                    isBlankCounter++
                } else {
                    error += " Birthplace,"
                }
            }

            textViewBirthday.text.toString().apply {
                if (this != "Select an item") {
                    isBlankCounter++
                } else {
                    error += " Birthday,"
                }
            }

            editTextTravels.text.toString().apply {
                if (!this.isBlank()) {
                    isBlankCounter++
                } else {
                    error += " Travels,"
                }
            }

            editTextPhone.text.toString().apply {
                if (!this.isBlank()) {
                    isBlankCounter++
                } else {
                    error += " Phone,"
                }
            }

            // Check if CreateDentalRecordActivity overrides the click method when calling this fragment
            if (clickListener != null) {
                val anamnesis =
                    mapOf(
                        "patient" to MainActivity.person,
                        "birthplace" to editTextBirthplace.text.toString(),
                        "birthday" to textViewBirthday.text.toString(),
                        "travels" to editTextTravels.text.toString(),
                        "phone" to editTextPhone.text.toString()
                    )

                // If the fields are not empty, return click true and anamnesis object
                if (isBlankCounter == 5) {
                    isBlankCounter = 0
                    clickListener?.onClick(true, anamnesis)
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

                    // If the fields are empty, return click false and anamnesis object
                    clickListener?.onClick(false, anamnesis)
                }
            }
        }
    }
}
