package com.example.covidtracker.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.model.ModelTest

class TestViewModel() : ViewModel() {

    var param: String? = null
    val headline = MutableLiveData<String>()
    private var list: Array<ModelTest>? = null
    val userMutableLiveData: MutableLiveData<Array<ModelTest>?> = MutableLiveData()

    constructor(param: String) : this() {
        this.param = param
        populateList()
        userMutableLiveData.value = list
    }

    private fun populateList() {

        when (param) {

            "settings" -> {

                list = arrayOf(
                    ModelTest("Contact Tracing", false),
                    ModelTest("COVID Check-In", false),
                    ModelTest("Data Protection Information Notice", true),
                    ModelTest("App Metrics", false),
                    ModelTest("Leave", false)
                )
            }


            "Choose your age range" -> {

                list = arrayOf(
                    ModelTest("Prefer not to say", false),
                    ModelTest("16-39", false),
                    ModelTest("40-59", true),
                    ModelTest("60+", false)
                )
            }


            "Choose your county" -> {

                list = arrayOf(
                    ModelTest("Prefer not to say", false),
                    ModelTest("Carlow", false),
                    ModelTest("Carlow", true),
                    ModelTest("Clare", false),
                    ModelTest("Cork", false),
                    ModelTest("Donegal", false),
                    ModelTest("Galway", false),
                    ModelTest("Kerry", false),
                    ModelTest("Kildare", false),
                    ModelTest("Kilkenny", false),
                    ModelTest("Laois", false),
                    ModelTest("Leitrim", false),
                    ModelTest("Limerick", false),
                    ModelTest("Longford", false),
                    ModelTest("Mayo", false),
                    ModelTest("Meath", false),
                    ModelTest("Monaghan", false),
                    ModelTest("Offaly", false),
                    ModelTest("Roscommon", false),
                    ModelTest("Sligo", false),
                    ModelTest("Tipperary", false),
                    ModelTest("Westmeath", false),
                    ModelTest("Wexford", false),
                    ModelTest("Wicklow", false)
                )
            }


            "Choose your locality" -> {

                list = arrayOf(
                    ModelTest("Prefer not to say", false),
                    ModelTest("Ashbourne", false),
                    ModelTest("Balbriggan", false),
                    ModelTest("Ballyboghil", false),
                    ModelTest("Ballyouster", false),
                    ModelTest("Balrothery", false),
                    ModelTest("Brittas", false),
                    ModelTest("Clonee Village", false),
                    ModelTest("Donabate", false),
                    ModelTest("Dublin 1", false),
                    ModelTest("Dublin 2", false),
                    ModelTest("Dublin 3", false),
                    ModelTest("Dublin 4", false),
                    ModelTest("Dublin 5", false),
                    ModelTest("Dublin 6", false),
                    ModelTest("Dublin 6W", false),
                    ModelTest("Dublin 7", false),
                    ModelTest("Dublin 8", false),
                    ModelTest("Dublin 9", false),
                    ModelTest("Dublin 10", false),
                    ModelTest("Dublin 11", false),
                    ModelTest("Dublin 12", false),
                    ModelTest("Dublin 13", false),
                    ModelTest("Dublin 14", false),
                    ModelTest("Dublin 15", false),
                    ModelTest("Dublin 16", false),
                    ModelTest("Dublin 17", false),
                    ModelTest("Dublin 18", false),
                    ModelTest("Dublin 19", false),
                    ModelTest("Dublin 20", false),
                    ModelTest("Dublin 21", false),
                    ModelTest("Dublin 22", false),
                    ModelTest("Dublin 23", false),
                    ModelTest("Dublin 24", false),
                    ModelTest("Garristown", false),
                    ModelTest("Glencullen", false),
                    ModelTest("Gormanston", false),
                    ModelTest("Kinsaley", false),
                    ModelTest("Kinsealy-Drinan", false),
                    ModelTest("Leixlip", false),
                    ModelTest("Loughshinny", false),
                    ModelTest("Malahide", false),
                    ModelTest("Naul", false),
                    ModelTest("Newcastle", false),
                    ModelTest("North Country Dublin", false),
                    ModelTest("Oldtown", false),
                    ModelTest("Portmarnock", false),
                    ModelTest("Portrane", false),
                    ModelTest("Rathcole", false),
                    ModelTest("Rivermeade", false),
                    ModelTest("Rush", false),
                    ModelTest("Saggart", false),
                    ModelTest("Skerries", false),
                    ModelTest("South Country Dublin", false),
                    ModelTest("Stamullen", false),
                    ModelTest("Swords", false)
                )

            }


        }

    }

}



