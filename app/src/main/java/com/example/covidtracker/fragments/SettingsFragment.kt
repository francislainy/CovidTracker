package com.example.covidtracker.fragments

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerSettingsItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity)
        rvSettings.layoutManager = linearLayoutManager

        adapter = GroupAdapter()


        adapter.add(RecyclerSettingsItem(activity as MainActivity, "Contact Tracing"))
        adapter.add(RecyclerSettingsItem(activity as MainActivity, "COVID Check-In"))
        adapter.add(RecyclerSettingsItem(activity as MainActivity, "Data Protection Information Notice"))
        adapter.add(RecyclerSettingsItem(activity as MainActivity, "Terms & Conditions"))
        adapter.add(RecyclerSettingsItem(activity as MainActivity, "App Metrics"))
        adapter.add(RecyclerSettingsItem(activity as MainActivity, "Leave"))

        rvSettings.adapter = adapter

        val decoration = DividerItemDecoration(
            activity as MainActivity,
            HORIZONTAL
        )
        rvSettings.addItemDecoration(decoration)
    }


    companion object {
        fun newInstance() =
            SettingsFragment()
    }
}