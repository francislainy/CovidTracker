package com.example.covidtracker.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.covidtracker.R
import com.example.covidtracker.api.GetCovidGeneralAPI
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.CovidGeneral
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_data_protection.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

    class DataProtectionFragment : Fragment(R.layout.fragment_data_protection) {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Data Protection Information Notice"

        val url = "https://hidden-dusk-75987.herokuapp.com/api/v1/covidTracker/termsHtml"

        getCovidGeneral(url)

    }


    private fun getCovidGeneral(fullUrl: String) {

        GetCovidGeneralAPI.postData(
            object : GetCovidGeneralAPI.ThisCallback {

                @RequiresApi(Build.VERSION_CODES.N)
                override fun onSuccess(jo: JsonObject) {

                    Log.i(LOG_TAG, "onSuccess $LOG_TAG")

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val covidGeneral =
                        gson.fromJson(jo, CovidGeneral::class.java)

                    updateUI(covidGeneral)

                }


                override fun onFailure(message: String?) {
                    Log.e(LOG_TAG, "onFailure $message $LOG_TAG")
                }


                override fun onError(apiError: APIError) {
                    Log.e(LOG_TAG, "onError $apiError $LOG_TAG")
                }

            },
            fullUrl
        )

    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun updateUI(covidGeneral: CovidGeneral) {
        tvContent.text = Html.fromHtml(
            covidGeneral.terms,
            Html.FROM_HTML_MODE_COMPACT
        )
    }


    companion object {

        val LOG_TAG = DataProtectionFragment::class.java.canonicalName
    }
}