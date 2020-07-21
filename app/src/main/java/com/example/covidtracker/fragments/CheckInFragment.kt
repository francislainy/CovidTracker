package com.example.covidtracker.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.Utils
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerHistoryItem
import com.example.covidtracker.model.MyDataList
import com.example.covidtracker.view_models.PostViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_check_in_bottom.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*
import java.text.SimpleDateFormat
import java.util.*

class CheckInFragment : Fragment(R.layout.fragment_check_in_bottom) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Your symptom history"


        val linearLayoutManager = LinearLayoutManager(activity)
        rvHistory.layoutManager = linearLayoutManager
        adapter = GroupAdapter()
        rvHistory.adapter = adapter


        val postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postViewModel.allPosts.observe(activity as MainActivity,
            Observer { posts: List<MyDataList?>? ->

                for (p in posts!!) {

                    val date = SimpleDateFormat("dd-MM").format(Date())



                    adapter!!.add(
                        RecyclerHistoryItem(
                            activity as MainActivity,
                            p?.status.toString(),
                            Utils.formatDate(Date())
                        )
                    )
                }
            }
        )

    }

}