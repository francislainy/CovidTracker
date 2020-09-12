package com.example.covidtracker.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.model.ModelDialogOption

class SharedViewModel : ViewModel() {
    val selected = MutableLiveData<ModelDialogOption>()

    fun select(item: ModelDialogOption) {
        selected.value = item
    }
}