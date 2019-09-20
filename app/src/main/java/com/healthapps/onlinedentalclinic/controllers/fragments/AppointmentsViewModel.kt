package com.healthapps.onlinedentalclinic.controllers.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppointmentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is appointment Fragment"
    }
    val text: LiveData<String> = _text
}