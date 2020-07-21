package com.example.covidtracker.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.DataRoomDbase
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerHistoryItem
import com.example.covidtracker.model.MyDataList
import com.example.covidtracker.view_models.PostViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_check_in_bottom.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class CheckInFragment : Fragment(R.layout.fragment_check_in_bottom) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null
    var myDatabase: DataRoomDbase? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Your symptom history"


        val linearLayoutManager = LinearLayoutManager(activity)
        rvHistory.layoutManager = linearLayoutManager
        adapter = GroupAdapter()
//        adapter!!.add(RecyclerHistoryItem(activity as MainActivity, "19th July"))
        rvHistory.adapter = adapter


        val postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postViewModel.allPosts.observe(activity as MainActivity,
            Observer { posts: List<MyDataList?>? ->
                Log.i("fran here again", posts?.get(0).toString())
                for (p in posts!!) {

                    adapter!!.add(
                        RecyclerHistoryItem(
                            activity as MainActivity,
                            p?.status.toString(),
                            p?.date.toString()
                        )
                    )
                }
            }
        )

    }

}