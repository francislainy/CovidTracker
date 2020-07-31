package com.example.covidtracker.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerCountyItem
import com.example.covidtracker.api.GetResponseAPI
import com.example.covidtracker.model.APIError
import com.example.covidtracker.model.Totals
import com.example.covidtracker.utils.addDecorationSkipLast
import com.example.covidtracker.view_models.HomeViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_cases_by_county.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*
import java.util.*
import kotlin.Comparator


class CasesByCountyFragment : Fragment(R.layout.fragment_cases_by_county) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null
    private var viewModel: HomeViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Cases by county"

        adapter = GroupAdapter()
        rvCounty.layoutManager = LinearLayoutManager(activity)
        rvCounty.addDecorationSkipLast(activity as MainActivity)
        rvCounty.adapter = adapter


        val url =
            "https://services1.arcgis.com/eNO7HHeQ3rUcBllm/arcgis/rest/services/CovidCountyStatisticsHPSCIrelandOsiView/FeatureServer/0/query?f=json&where=1%3D1&returnGeometry=false&spatialRel=esriSpatialRelIntersects&outFields=*&groupByFieldsForStatistics=CountyName&orderByFields=value%20desc&outStatistics=%5B%7B%22statisticType%22%3A%22avg%22%2C%22onStatisticField%22%3A%22ConfirmedCovidCases%22%2C%22outStatisticFieldName%22%3A%22value%22%7D%5D&resultType=standard&cacheHint=true"

        getTotals(url)


//        viewModel = ViewModelProviders.of(activity as MainActivity).get(HomeViewModel::class.java)
//
//        val myViewModel = ViewModelProvider(
//            this, MyViewModelFactory("settings")
//        ).get(HomeViewModel::class.java)
//
//        myViewModel.userMutableLiveData.observe(viewLifecycleOwner, userListUpdateObserver)

    }


    private fun getTotals(fullUrl: String) {

        GetResponseAPI.postData(
            object : GetResponseAPI.ThisCallback {

                @RequiresApi(Build.VERSION_CODES.N)
                override fun onSuccess(jo: JsonObject) {

                    Log.i(LOG_TAG, "onSuccess $LOG_TAG")

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val totals: Totals =
                        gson.fromJson(jo, Totals::class.java)

                    val preference = (activity as MainActivity).getSharedPreferences(
                        resources.getString(R.string.app_name),
                        Context.MODE_PRIVATE
                    )
                    val total = preference.getInt("total", 0)


                    if (totals.features!!.isNotEmpty()) {
                        Collections.sort(
                            totals.features,
                            Comparator { c1, c2 -> //You should ensure that list doesn't contain null values!
                                c1.attributes!!.countyName!!.compareTo(c2.attributes?.countyName!!)
                            })
                    }


                    for (f in totals.features!!) {

                        val attributes = f.attributes
                        val countyName = attributes?.countyName
                        val value = attributes?.value

                        adapter?.add(
                            RecyclerCountyItem(
                                activity as MainActivity,
                                countyName,
                                value,
                                total
                            )
                        )

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

        private val LOG_TAG = CasesByCountyFragment::class.java.canonicalName
    }
}