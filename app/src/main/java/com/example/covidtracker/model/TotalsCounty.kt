package com.example.covidtracker.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TotalsCounty {
    @SerializedName("objectIdFieldName")
    @Expose
    var objectIdFieldName: String? = null

    @SerializedName("uniqueIdField")
    @Expose
    var uniqueIdField: UniqueIdField? = null

    @SerializedName("globalIdFieldName")
    @Expose
    var globalIdFieldName: String? = null

    @SerializedName("geometryType")
    @Expose
    var geometryType: String? = null

    @SerializedName("fields")
    @Expose
    var fields: List<Field>? = null

    @SerializedName("features")
    @Expose
    var features: List<Feature>? = null
}