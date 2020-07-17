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
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_dialog.*


class MyDialogFragment : DialogFragment() {

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

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

        when(arguments?.getString("headerText")) {
            "Choose your age range" -> {
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Prefer not to say"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "16-39"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "40-59"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "60+"))
            }
            "Choose your county" -> {
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Prefer not to say"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Carlow"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Cavan"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Clare"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Cork"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Donegal"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Galway"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Kerry"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Kildare"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Kilkenny"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Laois"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Leitrim"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Limerick"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Longford"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Mayo"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Meath"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Monaghan"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Offaly"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Roscommon"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Sligo"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Tipperary"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Waterford"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Westmeath"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Wexford"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Wicklow"))
            }
            "Choose your locality" -> {
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Prefer not to say"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Ashbourne"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Balbriggan"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Ballyboghil"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Ballyouster"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Balrothery"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Brittas"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Clonee Village"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Donabate"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 1"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 2"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 3"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 4"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 5"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 6"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 6W"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 7"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 8"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 9"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 10"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 11"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 12"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 13"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 14"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 15"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 16"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 17"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 18"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 19"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 20"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 21"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 22"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 23"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Dublin 24"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Garristown"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Glencullen"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Gormanston"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Kinsaley"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Kinsealy-Drinan"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Leixlip"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Loughshinny"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Malahide"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Naul"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Newcastle"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "North Country Dublin"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Oldtown"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Portmarnock"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Portrane"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Rathcole"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Rivermeade"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Rush"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Saggart"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Skerries"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "South Country Dublin"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Stamullen"))
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Swords"))
            }
        }


        rvOptions.adapter = adapter
    }
}