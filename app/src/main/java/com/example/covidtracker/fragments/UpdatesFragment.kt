package com.example.covidtracker.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.api.GetTotalsAPI
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.Totals
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.national_totals_layout.*
import kotlinx.android.synthetic.main.todays_fight_layout.*

private val LOG_TAG = UpdatesFragment::class.java.canonicalName

class UpdatesFragment : Fragment() {

    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_updates, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        tvTotalsFightTitle.setOnClickListener {
            navController!!.navigate(R.id.action_updatesFragment_to_settingsFragment)
        }




        getTotals(
            "confirmed_cases",
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/Covid19StatisticsProfileHPSCIrelandView/FeatureServer/0/query?f=json&outStatistics=%5B%7B%22onStatisticField%22%3A%22TotalConfirmedCovidCases%22%2C%22outStatisticFieldName%22%3A%22TotalConfirmedCovidCases_max%22%2C%22statisticType%22%3A%22max%22%7D%5D"
        )

        getTotals(
            "total_deaths",
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/Covid19StatisticsProfileHPSCIrelandView/FeatureServer/0/query?f=json&where=1%3D1&outFields=*&returnGeometry=false&outStatistics=%5B%7B%22onStatisticField%22%3A%22TotalCovidDeaths%22%2C%22outStatisticFieldName%22%3A%22TotalCovidDeaths_max%22%2C%22statisticType%22%3A%22max%22%7D%5D"
        )

        getTotals(
            "total_hospitalised",
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/Covid19StatisticsProfileHPSCIrelandView/FeatureServer/0/query?f=json&where=1%3D1&outFields=*&returnGeometry=false&outStatistics=%5B%7B%22onStatisticField%22%3A%22HospitalisedCovidCases%22%2C%22outStatisticFieldName%22%3A%22HospitalisedCovidCases_max%22%2C%22statisticType%22%3A%22max%22%7D%5D"
        )

        getTotals(
            "total_required_cpu",
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/Covid19StatisticsProfileHPSCIrelandView/FeatureServer/0/query?f=json&where=1%3D1&outFields=*&returnGeometry=false&outStatistics=%5B%7B%22onStatisticField%22%3A%22RequiringICUCovidCases%22%2C%22outStatisticFieldName%22%3A%22RequiringICUCovidCases_max%22%2C%22statisticType%22%3A%22max%22%7D%5D"
        )

    }


    private fun getTotals(section: String, fullUrl: String) {

        GetTotalsAPI.postData(
            object : GetTotalsAPI.ThisCallback {

                override fun onSuccess(jo: JsonObject) {

                    Log.i(LOG_TAG, "onSuccess $LOG_TAG")


                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val totals: Totals =
                        gson.fromJson(jo, Totals::class.java)

                    when (section) {
                        "confirmed_cases" -> {
                            tvTotalCases.text =
                                totals.features?.get(0)?.attributes?.totalConfirmedCovidCasesMax.toString()
                        }
                        "total_deaths" -> {
                            tvTotalDeaths.text =
                                totals.features?.get(0)?.attributes?.totalCovidDeathsMax.toString()
                        }
                        "total_hospitalised" -> {
                            tvTotalHospitalised.text =
                                totals.features?.get(0)?.attributes?.totalHospitalisedCovidCasesMax.toString()
                        }
                        "total_required_cpu" -> {
                            tvTotalRequiredIcu.text =
                                totals.features?.get(0)?.attributes?.totalRequiringICUCovidCasesMax.toString()
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
        fun newInstance() =
            UpdatesFragment()
    }
}