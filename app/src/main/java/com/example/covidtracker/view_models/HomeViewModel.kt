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

        when (param) {

            "settings" -> {

                list = arrayOf(
                    "Contact Tracing",
                    "COVID Check-In",
                    "Data Protection Information Notice",
                    "Terms & Conditions",
                    "App Metrics",
                    "Leave"
                )
            }


            "Choose your age range" -> {

                list = arrayOf(
                    "Prefer not to say",
                    "16-39",
                    "40-59",
                    "60+"
                )

            }


            "Choose your county" -> {

                list = arrayOf(
                    "Prefer not to say",
                    "Carlow",
                    "Cavan",
                    "Clare",
                    "Cork",
                    "Donegal",
                    "Dublin",
                    "Galway",
                    "Kerry",
                    "Kildare",
                    "Kilkenny",
                    "Laois",
                    "Leitrim",
                    "Limerick",
                    "Longford",
                    "Mayo",
                    "Meath",
                    "Monaghan",
                    "Offaly",
                    "Roscommon",
                    "Sligo",
                    "Tipperary",
                    "Waterford",
                    "Westmeath",
                    "Wexford",
                    "Wicklow"
                )

            }


            "Choose your locality" -> {

                list = arrayOf(
                    "Prefer not to say",
                    "Ashbourne",
                    "Balbriggan",
                    "Ballyboghil",
                    "Ballyouster",
                    "Balrothery",
                    "Brittas",
                    "Clonee Village",
                    "Donabate",
                    "Dublin 1",
                    "Dublin 2",
                    "Dublin 3",
                    "Dublin 4",
                    "Dublin 5",
                    "Dublin 6",
                    "Dublin 6W",
                    "Dublin 7",
                    "Dublin 8",
                    "Dublin 9",
                    "Dublin 10",
                    "Dublin 11",
                    "Dublin 12",
                    "Dublin 13",
                    "Dublin 14",
                    "Dublin 15",
                    "Dublin 16",
                    "Dublin 17",
                    "Dublin 18",
                    "Dublin 19",
                    "Dublin 20",
                    "Dublin 21",
                    "Dublin 22",
                    "Dublin 23",
                    "Dublin 24",
                    "Garristown",
                    "Glencullen",
                    "Gormanston",
                    "Kinsaley",
                    "Kinsealy-Drinan",
                    "Leixlip",
                    "Loughshinny",
                    "Malahide",
                    "Naul",
                    "Newcastle",
                    "North Country Dublin",
                    "Oldtown",
                    "Portmarnock",
                    "Portrane",
                    "Rathcole",
                    "Rivermeade",
                    "Rush",
                    "Saggart",
                    "Skerries",
                    "South Country Dublin",
                    "Stamullen",
                    "Swords"
                )

            }


        }

    }

}