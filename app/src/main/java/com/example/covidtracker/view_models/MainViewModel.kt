package com.example.covidtracker.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.model.ModelDialogOption

class MainViewModel : ViewModel() {

    private var list: ArrayList<ModelDialogOption>? = null
    private var param: String? = null

    val userMutableLiveData: MutableLiveData<ArrayList<ModelDialogOption>?> = MutableLiveData()


    fun updateItem(position: Int) {
        val itemToUpdate = list!!.get(position)
        itemToUpdate.selected = !itemToUpdate.selected!!
        list!![position] = itemToUpdate
    }


    fun flushItems() {
        userMutableLiveData.value = list!!
    }


    fun updateList(param: String) {

        list = ArrayList()
        when (param) {

            "Choose your age range" -> {

                list!!.add(ModelDialogOption("Prefer not to say", false))
                list!!.add(ModelDialogOption("16-39", false))
                list!!.add(ModelDialogOption("40-59", false))
                list!!.add(ModelDialogOption("60+", false))

            }

            "Choose your county" -> {

                list!!.add(ModelDialogOption("Prefer not to say", false))
                list!!.add(ModelDialogOption("County1", false))
                list!!.add(ModelDialogOption("County2", false))
                list!!.add(ModelDialogOption("County3", false))

            }
            "Choose your locality" -> {
                list!!.add(ModelDialogOption("Prefer not to say", false))
                list!!.add(ModelDialogOption("Locality1", false))
                list!!.add(ModelDialogOption("Locality2", false))
                list!!.add(ModelDialogOption("Locality3", false))
            }
        }
        flushItems()
    }


}