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

class AppointmentFragment : Fragment() {

    private lateinit var homeViewModel: AppointmentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(AppointmentsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_appointments, container, false)
        val textView: TextView = root.findViewById(R.id.text_appointments)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}