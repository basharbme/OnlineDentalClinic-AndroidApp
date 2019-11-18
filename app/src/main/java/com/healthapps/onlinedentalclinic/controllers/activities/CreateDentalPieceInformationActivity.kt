package com.healthapps.onlinedentalclinic.controllers.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.healthapps.onlinedentalclinic.R
import kotlinx.android.synthetic.main.activity_create_dental_piece_information.*

class CreateDentalPieceInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_dental_piece_information)

        //Set imageView from other activity
        val imageViewIndex: Int? = intent.extras!!.getInt("imageViewIndex")
        val codeDentalPiece: String? = intent.extras!!.getString("number")

        // Counter the complete fields
        var isBlankCounter = 0

        // Error that describing the empty fields
        var error = "Enter data in:"

        Glide.with(this).load(imageViewIndex).into(imageView_dental_piece_information)
        textViewCode.text = codeDentalPiece

        button_save_dental_piece_information.setOnClickListener {
            editTextDescription.text.apply {
                if (this.isBlank()) {
                    isBlankCounter ++
                    error += " Description, "
                }
            }

            if (isBlankCounter == 0 && error == "Enter data in:") {
                val intentResult = Intent()

                intentResult.putExtra("code", textViewCode.text.toString())
                intentResult.putExtra("description", editTextDescription.text.toString())

                setResult(Activity.RESULT_OK, intentResult)

                finish()
            } else {
                error += " to continue"
                MaterialAlertDialogBuilder(
                    this,
                    R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered
                )
                    .setTitle("Error: Incomplete fields")
                    .setMessage(error)
                    .setPositiveButton("OK", null)
                    .show()

                error = "Enter data in:"
                isBlankCounter = 0
            }
        }

    }
}
