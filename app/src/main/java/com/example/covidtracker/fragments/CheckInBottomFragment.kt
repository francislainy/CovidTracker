package com.example.covidtracker.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerHistoryItem
import com.example.covidtracker.model.MyDataList
import com.example.covidtracker.utils.Utils
import com.example.covidtracker.utils.addDecorationSkipLast
import com.example.covidtracker.utils.gone
import com.example.covidtracker.utils.visible
import com.example.covidtracker.view_models.PostViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_check_in_bottom.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*
import java.text.SimpleDateFormat

class CheckInBottomFragment : Fragment(R.layout.fragment_check_in_bottom) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null
    private var navController: NavController? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Your symptom history"


        rvHistory.layoutManager = LinearLayoutManager(activity)
        adapter = GroupAdapter()
        rvHistory.adapter = adapter

        rvHistory.addDecorationSkipLast(activity as MainActivity)


        navController = Navigation.findNavController(view)

        clLetUsKnowSymptomsParent.setOnClickListener {
            navController!!.navigate(R.id.action_checkInBottomFragment_to_howYouFeelingFragment)
        }


        val preference = (activity as MainActivity).getSharedPreferences(
            resources.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )


        val hasChecked = preference.getBoolean(getString(R.string.hasCheckedPreference), false)


        if (hasChecked) {
            clCanCheckAgainTomorrowParent.visible()
            clLetUsKnowSymptomsParent.gone()
        } else {
            clCanCheckAgainTomorrowParent.gone()
            clLetUsKnowSymptomsParent.visible()
        }


    }


    override fun onResume() {
        super.onResume()

        val postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        postViewModel.allPosts.observe(activity as MainActivity,
            Observer { posts: List<MyDataList?>? ->

                if (activity !== null) {

                    for (p in posts!!) {

                        val date =
                            SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(p?.date)
                        adapter!!.add(
                            RecyclerHistoryItem(
                                activity as MainActivity,
                                p?.status,
                                Utils.formatDate(date)
                            )
                        )
                    }
                }
            }
        )
    }
}