package com.example.covidtracker.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private var list: Array<String>? = null
    val userMutableLiveData: MutableLiveData<Array<String>?> = MutableLiveData()

    private fun init() {
        populateList()
        userMutableLiveData.value = list
    }

    private fun populateList() {

        list = arrayOf(
            "Contact Tracing",
            "COVID Check-In",
            "Data Protection Information Notice",
            "Terms & Conditions",
            "App Metrics",
            "Leave"
        )

    }

    init {
        init()
    }
}