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

        val linearLayoutManager = LinearLayoutManager(activity)
        rvOptions.layoutManager = linearLayoutManager

        adapter = GroupAdapter()

        adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "C"))
        adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Contact Tracing"))
        adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "COVID Check-In"))
        adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Data Protection Information Notice"))
        adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Terms & Conditions"))
        adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "App Metrics"))
        adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, "Leave"))

        rvOptions.adapter = adapter
    }
}