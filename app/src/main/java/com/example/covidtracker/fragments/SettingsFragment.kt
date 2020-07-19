package com.example.covidtracker.fragments

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.graphics.drawable.ClipDrawable.VERTICAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerSettingsItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*


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


        val list = arrayOf(
            "Contact Tracing",
            "COVID Check-In",
            "Data Protection Information Notice",
            "Terms & Conditions",
            "App Metrics",
            "Leave"
        )

        for (s in list) {
            adapter.add(RecyclerSettingsItem(activity as MainActivity, s))
        }


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