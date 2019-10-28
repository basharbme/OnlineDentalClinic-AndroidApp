package com.healthapps.onlinedentalclinic.controllers.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.healthapps.onlinedentalclinic.R

class OdontogramFragment : Fragment() {

    private lateinit var helpViewModel: HelpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        helpViewModel =
            ViewModelProviders.of(this).get(HelpViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_odontograms, container, false)
        val textView: TextView = root.findViewById(R.id.text_help)
        helpViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

}
