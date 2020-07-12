package com.example.covidtracker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Attributes {

    @SerializedName("TotalConfirmedCovidCases_max")
    @Expose
    var totalConfirmedCovidCasesMax: Int? = null

    @SerializedName("TotalCovidDeaths_max")
    @Expose
    var totalCovidDeathsMax: Int? = null

    @SerializedName("HospitalisedCovidCases_max")
    @Expose
    var totalHospitalisedCovidCasesMax: Int? = null

    @SerializedName("RequiringICUCovidCases_max")
    @Expose
    var totalRequiringICUCovidCasesMax: Int? = null

}