package com.healthapps.onlinedentalclinic.controllers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.healthapps.onlinedentalclinic.R

class DentalInfoFragment : Fragment() {

    private lateinit var dashboardViewModel: DentalInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DentalInfoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dental_info, container, false)
        val textView: TextView = root.findViewById(R.id.text_detal_info)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}