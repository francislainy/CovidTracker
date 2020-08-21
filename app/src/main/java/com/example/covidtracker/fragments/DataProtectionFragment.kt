package com.example.covidtracker.fragments

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.covidtracker.R
import com.example.covidtracker.api.GetResponseAPI
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.CovidGeneral
import com.example.covidtracker.utils.gone
import com.example.covidtracker.utils.visible
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_app_metrics.*
import kotlinx.android.synthetic.main.fragment_data_protection.*
import kotlinx.android.synthetic.main.fragment_data_protection.clConsent
import kotlinx.android.synthetic.main.fragment_data_protection.ivLockPrivacy
import kotlinx.android.synthetic.main.fragment_data_protection.snippedToolbar
import kotlinx.android.synthetic.main.fragment_data_protection.tvLinkDataProtection
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class DataProtectionFragment : Fragment(R.layout.fragment_data_protection) {

    private var navController: NavController? = null

    override fun onResume() {
        super.onResume()

        val args: AppMetricsFragmentArgs by navArgs()

        if (args.state == "start") {

            (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
            snippedToolbar.visible()
            ivSettingsCog.gone()
            tvSettings.gone()

        } else {
            (activity as AppCompatActivity?)!!.supportActionBar!!.show()
            snippedToolbar.gone()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DataProtectionFragmentArgs by navArgs()

        tvHeader.text = args.section
        

        navController = Navigation.findNavController(view)

        val bundle = bundleOf("state" to "start")


        if (args.state == "start") {

            tvContent.text = "The COVID Tracker app protects your privacy and does not share personal information about you with other app users. **Your identity will never be revealed to other app users.**\\n\\nAny personal data you provide will be processed in line with GDPR and data protection law. You can read more about this in the Data Protection Information Notice below. **Your data will only be used in relation to the COVID-19 pandemic response** as set out in the Data Protection Information Notice.\\n\\nIf you need to be alerted, the app will start this process with a secure in-app notification. Separate to using this app, the HSE contact tracing team may phone you if someone with COVID-19 identifies you as a close contact. If you have a COVID-19 test, the HSE will contact you by text or by phone with results.\\n\\nTake care with any suspicious phone calls, emails or texts asking you for personal information. **The HSE will not ask you for personal information by text or email."

        } else {
            ivLockPrivacy.gone()
            tvLinkDataProtection.gone()
            clConsent.gone()


            val url = "https://hidden-dusk-75987.herokuapp.com/api/v1/covidTracker/termsHtml"

            getResponseApi(url)

        }

        btnContinue.setOnClickListener {
            navController!!.navigate(
                R.id.action_dataProtectionFragment_to_appMetricsFragment,
                bundle
            )
        }

    }


    private fun getResponseApi(fullUrl: String) {

        GetResponseAPI.postData(
            object : GetResponseAPI.ThisCallback {

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