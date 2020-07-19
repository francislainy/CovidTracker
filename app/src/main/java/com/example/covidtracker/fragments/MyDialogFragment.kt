package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerDialogOptionsItem
import com.example.covidtracker.adapter_holders.RecyclerSettingsItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_dialog.*


class MyDialogFragment : DialogFragment() {

    private var list: Array<String>? = null
    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_Custom)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tvChoose.text = arguments?.getString("headerText")

        val linearLayoutManager = LinearLayoutManager(activity)
        rvOptions.layoutManager = linearLayoutManager

        adapter = GroupAdapter()






        when (arguments?.getString("headerText")) {

            "Choose your age range" -> {

                list = arrayOf(
                    "Prefer not to say",
                    "16-39",
                    "40-59",
                    "60+"
                )

            }

            "Choose your county" -> {

                list = arrayOf(
                    "Prefer not to say",
                    "Carlow",
                    "Cavan",
                    "Clare",
                    "Cork",
                    "Donegal",
                    "Dublin",
                    "Galway",
                    "Kerry",
                    "Kildare",
                    "Kilkenny",
                    "Laois",
                    "Leitrim",
                    "Limerick",
                    "Longford",
                    "Mayo",
                    "Meath",
                    "Monaghan",
                    "Offaly",
                    "Roscommon",
                    "Sligo",
                    "Tipperary",
                    "Waterford",
                    "Westmeath",
                    "Wexford",
                    "Wicklow"
                )

            }

            "Choose your locality" -> {

                list = arrayOf(
                    "Prefer not to say",
                    "Ashbourne",
                    "Balbriggan",
                    "Ballyboghil",
                    "Ballyouster",
                    "Balrothery",
                    "Brittas",
                    "Clonee Village",
                    "Donabate",
                    "Dublin 1",
                    "Dublin 2",
                    "Dublin 3",
                    "Dublin 4",
                    "Dublin 5",
                    "Dublin 6",
                    "Dublin 6W",
                    "Dublin 7",
                    "Dublin 8",
                    "Dublin 9",
                    "Dublin 10",
                    "Dublin 11",
                    "Dublin 12",
                    "Dublin 13",
                    "Dublin 14",
                    "Dublin 15",
                    "Dublin 16",
                    "Dublin 17",
                    "Dublin 18",
                    "Dublin 19",
                    "Dublin 20",
                    "Dublin 21",
                    "Dublin 22",
                    "Dublin 23",
                    "Dublin 24",
                    "Garristown",
                    "Glencullen",
                    "Gormanston",
                    "Kinsaley",
                    "Kinsealy-Drinan",
                    "Leixlip",
                    "Loughshinny",
                    "Malahide",
                    "Naul",
                    "Newcastle",
                    "North Country Dublin",
                    "Oldtown",
                    "Portmarnock",
                    "Portrane",
                    "Rathcole",
                    "Rivermeade",
                    "Rush",
                    "Saggart",
                    "Skerries",
                    "South Country Dublin",
                    "Stamullen",
                    "Swords"
                )

            }

        }


        for (s in list!!) {
            adapter.add(RecyclerSettingsItem(activity as MainActivity, s))
        }

        rvOptions.adapter = adapter


        tvClose.setOnClickListener {
            this.dismiss()
        }
    }
}