package com.example.covidtracker.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel() : ViewModel() {

    var param: String? = null
    private var list: Array<String>? = null
    val userMutableLiveData: MutableLiveData<Array<String>?> = MutableLiveData()


    constructor(param: String) : this() {
        this.param = param
        populateList()
        userMutableLiveData.value = list
    }


    private fun populateList() {

        if (param.equals("settings")) {

            list = arrayOf(
                "Contact Tracing",
                "COVID Check-In",
                "Data Protection Information Notice",
                "Terms & Conditions",
                "App Metrics",
                "Leave"
            )
        }

    }
}