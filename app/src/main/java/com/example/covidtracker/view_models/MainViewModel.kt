package com.example.covidtracker.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.model.ModelDialogOption

class MainViewModel : ViewModel() {

    private var list: ArrayList<ModelDialogOption>? = null

    val userMutableLiveData: MutableLiveData<ArrayList<ModelDialogOption>?> = MutableLiveData()

    init {
        populateList()
        userMutableLiveData.value = list!!
    }

    val selected = MutableLiveData<ModelDialogOption>()

    fun select(item:  ModelDialogOption) {
        selected.value = item
    }


    fun updateList() {

        list!!.add(ModelDialogOption("HHH", false))
        list!!.add(ModelDialogOption("1HH", false))
        list!!.add(ModelDialogOption("asdf", true))
        list!!.add(ModelDialogOption("asdf+", false))
        userMutableLiveData.value = list!!
    }

    private fun populateList() {

        list = ArrayList()

        list!!.add(ModelDialogOption("Prefer not to say", false))
        list!!.add(ModelDialogOption("16-39", false))
        list!!.add(ModelDialogOption("40-59", true))
        list!!.add(ModelDialogOption("60+", false))

    }

}