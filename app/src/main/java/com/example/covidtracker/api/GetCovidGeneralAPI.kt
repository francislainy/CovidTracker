package com.example.covidtracker.api

import android.util.Log
import com.example.covidtracker.utils.ErrorUtils
import com.example.covidtracker.model.APIError
import com.example.covidtracker.network.ApiInterface
import com.example.covidtracker.network.ServiceBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GetCovidGeneralAPI {

    private val LOG_TAG = GetCovidGeneralAPI::class.java.canonicalName

    interface ThisCallback {

        fun onSuccess(totals: JsonObject)

        fun onFailure(message: String?)

        fun onError(apiError: APIError)
    }


    /* GET */
    fun postData(callback: ThisCallback, fullUrl: String) {

        val service = ServiceBuilder.buildService(ApiInterface::class.java)
        val call = service.getCovidGeneral(
            fullUrl
        )


        call.enqueue(object : Callback<JsonObject> {

            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {

                try {

                    if (response.isSuccessful) {

                        callback.onSuccess(response.body())

                        Log.i(LOG_TAG, response.raw().toString())

                    } else {

                        Log.e(LOG_TAG, response.raw().toString())

                        // parse the response body...
                        val error: APIError = ErrorUtils.parseError(response)

                        // ... object to use it to show error information
                        callback.onError(error)

                    }

                } catch (e: Exception) {

                    Log.e(LOG_TAG, "Exception: " + e.message)

                    callback.onFailure(e.message)

                }

            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                Log.e(LOG_TAG, "onFailure: " + t.message)
                callback.onFailure(t!!.message)

            }

        })

    }

}