package com.example.covidtracker.fragments

import android.content.Context
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
import com.example.covidtracker.api.GetResponseAPI
import com.example.covidtracker.db.DataRoomDbase
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.MyDataList
import com.example.covidtracker.model.Totals
import com.example.covidtracker.utils.gone
import com.example.covidtracker.utils.invisible
import com.example.covidtracker.utils.visible
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.covid_spreading_layout.*
import kotlinx.android.synthetic.main.fragment_check_in_bottom.*
import kotlinx.android.synthetic.main.fragment_updates.*
import kotlinx.android.synthetic.main.great_to_hear_layout.*
import kotlinx.android.synthetic.main.how_you_feeling_layout.*
import kotlinx.android.synthetic.main.how_you_feeling_layout.ivClose
import kotlinx.android.synthetic.main.national_totals_layout.*
import kotlinx.android.synthetic.main.title_and_progress_bar.view.*
import kotlinx.android.synthetic.main.todays_fight_layout.view.*


class UpdatesFragment : Fragment(R.layout.fragment_updates) {

    private var navController: NavController? = null
    private var myDatabase: DataRoomDbase? = null

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

        tvWelcomeBack.visible()
        ivClose.visible()


        retrieveTotals()

        getResponseApi(fullUrl = "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/CovidStatisticsProfileHPSCIrelandOpenData/FeatureServer/0/query?f=json&where=Date%3Etimestamp%20%272020-03-17%2023%3A59%3A59%27&returnGeometry=false&spatialRel=esriSpatialRelIntersects&outFields=*&orderByFields=StatisticsProfileDate%20asc&resultOffset=0&resultRecordCount=32000&resultType=standard&cacheHint=true");

        getResponseApi(
            "graph",
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/CovidStatisticsProfileHPSCIrelandOpenData/FeatureServer/0/query?f=json&where=1%3D1&returnGeometry=false&spatialRel=esriSpatialRelIntersects&outFields=*&orderByFields=Date%20asc&resultOffset=0&resultRecordCount=32600&resultType=standard&cacheHint=true"
        )


        navController = Navigation.findNavController(view)

        nationalPictureLayout.setOnClickListener {
            navController!!.navigate(R.id.action_updatesFragment_to_casesByCountyFragment)
        }


        val preference = (activity as MainActivity).getSharedPreferences(
            resources.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
        val hasCheckedToday = preference.getBoolean("hasCheckedToday", false)

        if (hasCheckedToday) {
            greatToHearLayout.visible()
            howYouFeelingLayout.gone()
        } else {
            greatToHearLayout.gone()
            howYouFeelingLayout.visible()
        }



        btnImGood.setOnClickListener(onClickStatusItemOnCard)
        btnImNotWell.setOnClickListener(onClickStatusItemOnCard)
        ivClose.setOnClickListener(onClickStatusItemOnCard)
        tvViewHistory.setOnClickListener(onClickStatusItemOnCard)

    }


    private fun addChart(totals: Totals) {

        val values = arrayOf<String>("jan", "feb", "mar")
        val xAxis = chart1.xAxis
//        xAxis.valueFormatter = MyValueFormatter(values)
//        xAxis.valueFormatter = IndexAxisValueFormatter(values)
        xAxis.enableGridDashedLine(10f, 10f, 0f)
        xAxis.setDrawGridLines(true)
        xAxis.setDrawAxisLine(true)

        chart1.apply {
            isDragEnabled = true
            isScaleXEnabled = false
            isScaleYEnabled = true
            axisRight.isEnabled = false
            axisLeft.setDrawAxisLine(false)
            xAxis.isEnabled = false
            legend.isEnabled = false
            description.text = ""
            setTouchEnabled(false)
        }


        val y: YAxis = chart1.axisLeft
        y.axisMaximum = 800f
        y.axisMinimum = 0f
        y.labelCount = 5

        val yValues = ArrayList<Entry>()

        for ((index, i) in totals.features!!.withIndex()) {

            yValues.add(Entry(index.toFloat(), i.attributes?.confirmedCovidCases!!.toFloat()))
//            yValues.add(Entry(index.toFloat(), index.toFloat()*100))

        }


        val set1 = LineDataSet(yValues, "Data set 1")
        set1.fillAlpha = 110
        set1.color = resources.getColor(R.color.orange)
        set1.valueTextSize = 0F
        set1.setDrawCircles(false)


        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        chart1.data = data

    }


    private fun retrieveTotals() {

        val baseUrl =
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/Covid19StatisticsProfileHPSCIrelandView/FeatureServer/0/query?f=json&where=1%3D1&outFields=*&returnGeometry=false&outStatistics=%5B%7B%22onStatisticField%22%3A%22"
        val appended = "%22%2C%22statisticType%22%3A%22max%22%7D%5D"


        getResponseApi(
            getString(R.string.confirmed_cases_param),
            baseUrl + "TotalConfirmedCovidCases%22%2C%22outStatisticFieldName%22%3A%22TotalConfirmedCovidCases_max" + appended
        )

        getResponseApi(
            "total_deaths",
            baseUrl + "TotalCovidDeaths%22%2C%22outStatisticFieldName%22%3A%22TotalCovidDeaths_max" + appended
        )

        getResponseApi(
            getString(R.string.total_hospitalised_param),
            baseUrl + "HospitalisedCovidCases%22%2C%22outStatisticFieldName%22%3A%22HospitalisedCovidCases_max" + appended
        )

        getResponseApi(
            getString(R.string.total_required_icu),
            baseUrl + "RequiringICUCovidCases%22%2C%22outStatisticFieldName%22%3A%22RequiringICUCovidCases_max" + appended
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private val onClickStatusItemOnCard = View.OnClickListener run@{

        val myDataList = MyDataList()
        myDataList.date = java.util.Date().toString()
        myDataList.hasRepliedToday = true
        it?.isEnabled = false


        myDatabase = DataRoomDbase.getDatabase(activity as MainActivity)


        when (it) {
            ivClose -> {
                howAreYouFeelingLayout.gone()
                return@run
            }
            tvViewHistory -> {
                navController!!.navigate(R.id.action_updatesFragment_to_checkInBottomFragment)
            }


            btnImGood -> {
                myDataList.status = "Good"
                howAreYouFeelingLayout.gone()
            }
            btnImNotWell -> {
                myDataList.status = "Bad"
                navController!!.navigate(R.id.action_updatesFragment_to_notWellSymptomsFragment)
            }
        }

        val preference = (activity as MainActivity).getSharedPreferences(
            resources.getString(R.string.app_name), Context.MODE_PRIVATE
        )
        val editor = preference.edit()
        editor.putBoolean("hasCheckedToday", true)
        editor.apply()

        myDatabase?.dataDAO()?.addData(myDataList)
    }


    private fun getResponseApi(section: String = "", fullUrl: String) {

        GetResponseAPI.postData(
            object : GetResponseAPI.ThisCallback {

                override fun onSuccess(jo: JsonObject) {

                    Log.i(LOG_TAG, "onSuccess $LOG_TAG")

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val totals: Totals =
                        gson.fromJson(jo, Totals::class.java)
                    var attributes = totals.features?.get(0)?.attributes

                    when (section) {

                        getString(R.string.confirmed_cases_param) -> {
                            val s = String.format(
                                "%,d",
                                attributes?.totalConfirmedCovidCasesMax.toString().toLong()
                            )
                            tvTotalCases.text = s

                            val preference = (activity as MainActivity).getSharedPreferences(
                                resources.getString(R.string.app_name), Context.MODE_PRIVATE
                            )
                            val editor = preference.edit()
                            editor.putInt("total", attributes?.totalConfirmedCovidCasesMax!!)
                            editor.apply()
                        }

                        getString(R.string.total_deaths_param) -> {
                            val s = String.format(
                                "%,d",
                                attributes?.totalCovidDeathsMax.toString().toLong()
                            )
                            tvTotalDeaths.text = s
                        }

                        getString(R.string.total_hospitalised_param) -> {
                            val s = String.format(
                                "%,d",
                                attributes?.totalHospitalisedCovidCasesMax.toString().toLong()
                            )
                            tvTotalHospitalised.text = s
                        }

                        getString(R.string.total_required_icu) -> {
                            val s = String.format(
                                "%,d",
                                attributes?.totalRequiringICUCovidCasesMax.toString().toLong()
                            )
                            tvTotalRequiredIcu.text = s
                        }

                        "graph" -> {

                            chart1.invisible() // For some reason, without hiding and showing the chart the data won't show and that's why this is here.
                            addChart(totals)
                            chart1.visible()
                        }

                        "" -> {

                            attributes =
                                totals.features?.get(totals.features!!.size - 1)?.attributes // last element contains most up to date value as we're getting them for multiple days period

                            val communityTransmission = attributes?.communityTransmission
                            val closeContact = attributes?.closeContact
                            val travelAbroad = attributes?.travelAbroad

                            tvTotalCommunity.text = "$communityTransmission %"
                            tvTotalCloseContact.text = "$closeContact %"
                            tvTotalTravelAbroad.text = "$travelAbroad %"

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


//private class MyValueFormatter2 :
//    ValueFormatter() {
//    override fun getFormattedValue(
//        value: Float,
//        entry: Entry,
//        dataSetIndex: Int,
//        viewPortHandler: ViewPortHandler
//    ): String {
//        // write your logic here
//        return if (value > 0) super.getFormattedValue(value) else ""
//    }
//}
//
//private class MyValueFormatter(values: Array<String>) :
//    IndexAxisValueFormatter(values) {
//
//    override fun getFormattedValue(
//        value: Float,
//        entry: Entry?,
//        dataSetIndex: Int,
//        viewPortHandler: ViewPortHandler?
//    ): String? {
//        for (i in 0 until values.size) {
//            if (values.get(i).equals(value)) {
//                return values.get(i)
//            }
//        }
//        return null
//    }
//}
//
//
//class MyAxisValueFormatter(values: Array<String>) : IndexAxisValueFormatter(values) {
//    private val mValues: Array<String> = values
//    override fun getFormattedValue(value: Float, axis: AxisBase): String {
//        return mValues[value.toInt()]
//    }
//}
//
//
//class MyXAxisValuesFormatter(values: Array<String>) : IndexAxisValueFormatter(values) {
//
//    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
//        return values[value.toInt()]
//    }
//
//}
