package com.example.covidtracker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Totals {

    @SerializedName("fields")
    @Expose
    var fields: List<Field>? = null

    @SerializedName("features")
    @Expose
    var features: List<Feature>? = null

}