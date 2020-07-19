package com.example.covidtracker.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class Communicator : ViewModel() {

    val message = MutableLiveData<Any>()

    fun setMsgCommunicator(msg: String) {
        message.value = msg
    }
}
