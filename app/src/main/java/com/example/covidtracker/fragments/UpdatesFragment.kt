package com.example.covidtracker.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.db.DataRoomDbase
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.api.GetTotalsAPI
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.MyDataList
import com.example.covidtracker.model.Totals
import com.example.covidtracker.utils.gone
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_updates.*
import kotlinx.android.synthetic.main.national_totals_layout.*
import kotlinx.android.synthetic.main.todays_fight_layout.*

class UpdatesFragment : Fragment(R.layout.fragment_updates) {

    private var navController: NavController? = null
    private var myDatabase: DataRoomDbase? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        tvTotalsFightTitle.setOnClickListener {
            navController!!.navigate(R.id.action_updatesFragment_to_settingsFragment)
        }

        val baseUrl =
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/Covid19StatisticsProfileHPSCIrelandView/FeatureServer/0/query?f=json&where=1%3D1&outFields=*&returnGeometry=false&outStatistics=%5B%7B%22onStatisticField%22%3A%22"
        val appended = "%22%2C%22statisticType%22%3A%22max%22%7D%5D"


        getTotals(
            "confirmed_cases",
            baseUrl + "TotalConfirmedCovidCases%22%2C%22outStatisticFieldName%22%3A%22TotalConfirmedCovidCases_max" + appended
        )

        getTotals(
            "total_deaths",
            baseUrl + "TotalCovidDeaths%22%2C%22outStatisticFieldName%22%3A%22TotalCovidDeaths_max" + appended
        )

        getTotals(
            "total_hospitalised",
            baseUrl + "HospitalisedCovidCases%22%2C%22outStatisticFieldName%22%3A%22HospitalisedCovidCases_max" + appended
        )

        getTotals(
            "total_required_icu",
            baseUrl + "RequiringICUCovidCases%22%2C%22outStatisticFieldName%22%3A%22RequiringICUCovidCases_max" + appended
        )


        btnImGood.setOnClickListener(onClickStatusItemOnCard)
        btnImNotWell.setOnClickListener(onClickStatusItemOnCard)

        ivClose.setOnClickListener(onClickStatusItemOnCard)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private val onClickStatusItemOnCard = View.OnClickListener run@{

        val myDataList = MyDataList()

        when (it) {
            ivClose -> {
                howAreYouFeelingLayout.gone()
                return@run
            }
            btnImGood -> myDataList.status = "Good"
            btnImNotWell -> myDataList.status = "Bad"
        }

        myDataList.date = java.util.Date().toString()
        myDataList.hasRepliedToday = true
        it?.isEnabled = false
        howAreYouFeelingLayout.gone()

        myDatabase = DataRoomDbase.getDatabase(activity as MainActivity)
        myDatabase?.dataDAO()?.addData(myDataList)
    }


    private fun getTotals(section: String, fullUrl: String) {

        GetTotalsAPI.postData(
            object : GetTotalsAPI.ThisCallback {

                override fun onSuccess(jo: JsonObject) {

                    Log.i(LOG_TAG, "onSuccess $LOG_TAG")

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val totals: Totals =
                        gson.fromJson(jo, Totals::class.java)
                    val attributes = totals.features?.get(0)?.attributes

                    when (section) {
                        "confirmed_cases" -> {
                            tvTotalCases.text =
                                attributes?.totalConfirmedCovidCasesMax.toString()
                        }
                        "total_deaths" -> {
                            tvTotalDeaths.text =
                                attributes?.totalCovidDeathsMax.toString()
                        }
                        "total_hospitalised" -> {
                            tvTotalHospitalised.text =
                                attributes?.totalHospitalisedCovidCasesMax.toString()
                        }
                        "total_required_icu" -> {
                            tvTotalRequiredIcu.text =
                                attributes?.totalRequiringICUCovidCasesMax.toString()
                        }

                    }

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


    companion object {

        private val LOG_TAG = UpdatesFragment::class.java.canonicalName
    }
}