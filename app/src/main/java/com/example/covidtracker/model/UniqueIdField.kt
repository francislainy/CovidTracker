package com.example.covidtracker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UniqueIdField {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("isSystemMaintained")
    @Expose
    var isSystemMaintained: Boolean? = null
}