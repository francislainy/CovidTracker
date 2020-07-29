package com.example.covidtracker.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET("")
    fun getResponse(
        @Url fullUrl: String?
    ): Call<JsonObject>

}