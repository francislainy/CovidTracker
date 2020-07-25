package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerSettingsItem
import com.example.covidtracker.utils.addDecoration
import com.example.covidtracker.view_models.HomeViewModel
import com.example.covidtracker.view_models.MyViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var gAdapter: GroupAdapter<GroupieViewHolder>? = null
    private var viewModel: HomeViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Settings"

        val linearLayoutManager = LinearLayoutManager(activity)
        gAdapter = GroupAdapter()
        rvSettings.apply {
            layoutManager = linearLayoutManager
            addDecoration(activity as MainActivity)
            adapter = gAdapter
        }


        viewModel = ViewModelProviders.of(activity as MainActivity).get(HomeViewModel::class.java)

        val myViewModel = ViewModelProvider(
            this, MyViewModelFactory("settings")
        ).get(HomeViewModel::class.java)

        myViewModel.userMutableLiveData.observe(viewLifecycleOwner, userListUpdateObserver)

    }


    private val userListUpdateObserver: Observer<Array<String>?> =
        Observer { userArrayList ->
            for (s in userArrayList!!) {
                gAdapter!!.add(RecyclerSettingsItem(activity as MainActivity, s))
            }
        }

    companion object {

    }
}