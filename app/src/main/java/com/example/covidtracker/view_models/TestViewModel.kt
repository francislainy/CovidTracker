package com.example.covidtracker.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.model.ModelDialogOption

class TestViewModel() : ViewModel() {

    var param: String? = null
    val headline = MutableLiveData<String>()
    private var list: Array<ModelDialogOption>? = null
    val userMutableLiveData: MutableLiveData<Array<ModelDialogOption>?> = MutableLiveData()

    constructor(param: String) : this() {
        this.param = param
        populateList()
        userMutableLiveData.value = list!!
    }

    private fun populateList() {

        when (param) {

            "settings" -> {

                list = arrayOf(
                    ModelDialogOption("Contact Tracing"),
                    ModelDialogOption("COVID Check-In"),
                    ModelDialogOption("Data Protection Information Notice"),
                    ModelDialogOption("App Metrics"),
                    ModelDialogOption("Leave")
                )
            }


            "Choose your age range" -> {

                list = arrayOf(
                    ModelDialogOption("Prefer not to say", false),
                    ModelDialogOption("16-39", false),
                    ModelDialogOption("40-59", true),
                    ModelDialogOption("60+", false)
                )
            }


            "Choose your county" -> {

                list = arrayOf(
                    ModelDialogOption("Prefer not to say", false),
                    ModelDialogOption("Carlow", false),
                    ModelDialogOption("Carlow", true),
                    ModelDialogOption("Clare", false),
                    ModelDialogOption("Cork", false),
                    ModelDialogOption("Donegal", false),
                    ModelDialogOption("Galway", false),
                    ModelDialogOption("Kerry", false),
                    ModelDialogOption("Kildare", false),
                    ModelDialogOption("Kilkenny", false),
                    ModelDialogOption("Laois", false),
                    ModelDialogOption("Leitrim", false),
                    ModelDialogOption("Limerick", false),
                    ModelDialogOption("Longford", false),
                    ModelDialogOption("Mayo", false),
                    ModelDialogOption("Meath", false),
                    ModelDialogOption("Monaghan", false),
                    ModelDialogOption("Offaly", false),
                    ModelDialogOption("Roscommon", false),
                    ModelDialogOption("Sligo", false),
                    ModelDialogOption("Tipperary", false),
                    ModelDialogOption("Westmeath", false),
                    ModelDialogOption("Wexford", false),
                    ModelDialogOption("Wicklow", false)
                )
            }


            "Choose your locality" -> {

                list = arrayOf(
                    ModelDialogOption("Prefer not to say", false),
                    ModelDialogOption("Ashbourne", false),
                    ModelDialogOption("Balbriggan", false),
                    ModelDialogOption("Ballyboghil", false),
                    ModelDialogOption("Ballyouster", false),
                    ModelDialogOption("Balrothery", false),
                    ModelDialogOption("Brittas", false),
                    ModelDialogOption("Clonee Village", false),
                    ModelDialogOption("Donabate", false),
                    ModelDialogOption("Dublin 1", false),
                    ModelDialogOption("Dublin 2", false),
                    ModelDialogOption("Dublin 3", false),
                    ModelDialogOption("Dublin 4", false),
                    ModelDialogOption("Dublin 5", false),
                    ModelDialogOption("Dublin 6", false),
                    ModelDialogOption("Dublin 6W", false),
                    ModelDialogOption("Dublin 7", false),
                    ModelDialogOption("Dublin 8", false),
                    ModelDialogOption("Dublin 9", false),
                    ModelDialogOption("Dublin 10", false),
                    ModelDialogOption("Dublin 11", false),
                    ModelDialogOption("Dublin 12", false),
                    ModelDialogOption("Dublin 13", false),
                    ModelDialogOption("Dublin 14", false),
                    ModelDialogOption("Dublin 15", false),
                    ModelDialogOption("Dublin 16", false),
                    ModelDialogOption("Dublin 17", false),
                    ModelDialogOption("Dublin 18", false),
                    ModelDialogOption("Dublin 19", false),
                    ModelDialogOption("Dublin 20", false),
                    ModelDialogOption("Dublin 21", false),
                    ModelDialogOption("Dublin 22", false),
                    ModelDialogOption("Dublin 23", false),
                    ModelDialogOption("Dublin 24", false),
                    ModelDialogOption("Garristown", false),
                    ModelDialogOption("Glencullen", false),
                    ModelDialogOption("Gormanston", false),
                    ModelDialogOption("Kinsaley", false),
                    ModelDialogOption("Kinsealy-Drinan", false),
                    ModelDialogOption("Leixlip", false),
                    ModelDialogOption("Loughshinny", false),
                    ModelDialogOption("Malahide", false),
                    ModelDialogOption("Naul", false),
                    ModelDialogOption("Newcastle", false),
                    ModelDialogOption("North Country Dublin", false),
                    ModelDialogOption("Oldtown", false),
                    ModelDialogOption("Portmarnock", false),
                    ModelDialogOption("Portrane", false),
                    ModelDialogOption("Rathcole", false),
                    ModelDialogOption("Rivermeade", false),
                    ModelDialogOption("Rush", false),
                    ModelDialogOption("Saggart", false),
                    ModelDialogOption("Skerries", false),
                    ModelDialogOption("South Country Dublin", false),
                    ModelDialogOption("Stamullen", false),
                    ModelDialogOption("Swords", false)
                )

            }


            "things_to_protect" -> {

                list = arrayOf(
                    ModelDialogOption("Stay at home."),
                    ModelDialogOption("Do not go to work."),
                    ModelDialogOption("Do not use public transport."),
                    ModelDialogOption("Do not have visitors at your home."),
                    ModelDialogOption("Do not visit others, even if you usually care for them."),
                    ModelDialogOption("Do not go to the shops or pharmacy unless it's absolutely necessary - where possible, order your groceries online or have some family or friends drop them off."),
                    ModelDialogOption("Keep away from older people, anyone with long-term medical conditions and pregnant women.")
                )

            }

        }

    }

}



