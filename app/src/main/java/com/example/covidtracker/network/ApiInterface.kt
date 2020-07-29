package com.example.covidtracker.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

//    @GET("eNO7HHeQ3rUcBllm/arcgis/rest/services/Covid19StatisticsProfileHPSCIrelandView/FeatureServer/0/query?f=json")
//    fun getTotals(
//        @Query("outStatistics") outStatistics: String?
//    ): Call<JsonObject>

    @GET("")
    fun getTotals(
        @Url fullUrl: String?
    ): Call<JsonObject>

    @GET("")
    fun getCovidGeneral(
        @Url fullUrl: String?
    ): Call<JsonObject>

    @GET("")
    fun getCovidSpreading(
        @Url fullUrl: String?
    ): Call<JsonObject>


}