package com.example.covidtracker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Feature {
    @SerializedName("attributes")
    @Expose
    var attributes: Attributes? = null

}