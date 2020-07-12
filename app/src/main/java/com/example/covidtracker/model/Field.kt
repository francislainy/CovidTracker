package com.example.covidtracker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Field {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("alias")
    @Expose
    var alias: String? = null

}