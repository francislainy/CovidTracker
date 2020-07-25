package com.example.covidtracker.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.api.GetTotalsAPI
import com.example.covidtracker.db.DataRoomDbase
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.MyDataList
import com.example.covidtracker.model.Totals
import com.example.covidtracker.utils.gone
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_updates.*
import kotlinx.android.synthetic.main.national_totals_layout.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*
import kotlinx.android.synthetic.main.title_and_progress_bar.view.*
import kotlinx.android.synthetic.main.todays_fight_layout.view.*


class UpdatesFragment : Fragment(R.layout.fragment_updates) {

    private var navController: NavController? = null
    private var myDatabase: DataRoomDbase? = null
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "mindorks-welcome"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val ll = todaysFightLayout as ConstraintLayout
        val ll2 = ll.titleAndProgressBar
        val tv = ll2.tvHeader
        tv.text = "Today's fight"


        val lla = nationalPictureLayout as ConstraintLayout
        val ll2a = lla.titleAndProgressBar
        val tv2 = ll2a.tvHeader
        tv2.text = "Latest Update"


        navController = Navigation.findNavController(view)

        tvHeader.setOnClickListener {
            navController!!.navigate(R.id.action_updatesFragment_to_settingsFragment)
        }

        nationalPictureLayout.setOnClickListener {
            navController!!.navigate(R.id.action_updatesFragment_to_casesByCountyFragment)
        }


        retrieveTotals()


        btnImGood.setOnClickListener(onClickStatusItemOnCard)
        btnImNotWell.setOnClickListener(onClickStatusItemOnCard)

        ivClose.setOnClickListener(onClickStatusItemOnCard)
    }

    private fun retrieveTotals() {
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
                            val s = String.format(
                                "%,d",
                                attributes?.totalConfirmedCovidCasesMax.toString().toLong()
                            )
                            tvTotalCases.text = s

                            val preference=(activity as MainActivity).getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
                            val editor=preference.edit()
                            editor.putInt("total", attributes?.totalConfirmedCovidCasesMax!!)
                            editor.apply()


                        }
                        "total_deaths" -> {
                            val s = String.format(
                                "%,d",
                                attributes?.totalCovidDeathsMax.toString().toLong()
                            )
                            tvTotalDeaths.text = s

                        }
                        "total_hospitalised" -> {
                            val s = String.format(
                                "%,d",
                                attributes?.totalHospitalisedCovidCasesMax.toString().toLong()
                            )
                            tvTotalHospitalised.text = s
                        }
                        "total_required_icu" -> {
                            val s = String.format(
                                "%,d",
                                attributes?.totalRequiringICUCovidCasesMax.toString().toLong()
                            )
                            tvTotalRequiredIcu.text = s
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