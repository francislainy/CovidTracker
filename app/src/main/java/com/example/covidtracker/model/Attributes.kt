package com.example.covidtracker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Attributes {
    @SerializedName("TotalConfirmedCovidCases_max")
    @Expose
    var totalConfirmedCovidCasesMax: Int? = null

}