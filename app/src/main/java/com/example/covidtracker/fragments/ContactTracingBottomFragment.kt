package com.example.covidtracker.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.api.GetResponseAPI
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.Totals
import com.example.covidtracker.utils.Utils.Companion.shareViaWhatsApp
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_contact_tracing_bottom.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class ContactTracingBottomFragment : Fragment(R.layout.fragment_contact_tracing_bottom) {

    var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        tvHeader.text = "Contact Tracing"

        btnShare.setOnClickListener { shareViaWhatsApp(activity as MainActivity) }
        closeContactInformationCard.setOnClickListener(onClickCard)
        uploadIdCard.setOnClickListener(onClickCard)


        getResponseApi(

            fullUrl = "https://hidden-dusk-75987.herokuapp.com/api/v1/covidTracker/getTotalRegistrations"
        )

    }


    private fun getResponseApi(section: String = "", fullUrl: String) {

        GetResponseAPI.postData(
            object : GetResponseAPI.ThisCallback {

                override fun onSuccess(jo: JsonObject) {

                    Log.i(LOG_TAG, "onSuccess $LOG_TAG")

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val totals: Totals =
                        gson.fromJson(jo, Totals::class.java)
                    var total = totals.total

                    tvNumberRegistrations.text = "$total %"

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


    private val onClickCard = View.OnClickListener {

        when (it) {
            closeContactInformationCard -> navController!!.navigate(R.id.action_contactTracingBottomFragment_to_closeContactFragment)
            uploadIdCard -> navController!!.navigate(R.id.action_contactTracingBottomFragment_to_uploadIdFragment)
        }

    }


    companion object {

        private val LOG_TAG = ContactTracingFragment::class.java.canonicalName
    }

}