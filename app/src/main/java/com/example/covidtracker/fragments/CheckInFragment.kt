package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerHistoryItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_check_in_bottom.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class CheckInFragment : Fragment(R.layout.fragment_check_in_bottom) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Your symptom history"

        val linearLayoutManager = LinearLayoutManager(activity)
        rvHistory.layoutManager = linearLayoutManager
        adapter = GroupAdapter()
        adapter!!.add(RecyclerHistoryItem(activity as MainActivity, "19th July"))
        adapter!!.add(RecyclerHistoryItem(activity as MainActivity, "19th July"))
        adapter!!.add(RecyclerHistoryItem(activity as MainActivity, "19th July"))
        adapter!!.add(RecyclerHistoryItem(activity as MainActivity, "19th July"))
        adapter!!.add(RecyclerHistoryItem(activity as MainActivity, "19th July"))


        rvHistory.adapter = adapter
    }

}