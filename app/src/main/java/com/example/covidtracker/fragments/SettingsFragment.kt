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
import com.example.covidtracker.utils.addDecorationSkipLast
import com.example.covidtracker.view_models.HomeViewModel
import com.example.covidtracker.view_models.MyViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null
    private var viewModel: HomeViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Settings"


        adapter = GroupAdapter()
        rvSettings.layoutManager = LinearLayoutManager(activity)
        rvSettings.addDecorationSkipLast(activity as MainActivity)
        rvSettings.adapter = adapter


        viewModel = ViewModelProviders.of(activity as MainActivity).get(HomeViewModel::class.java)

        val myViewModel = ViewModelProvider(
            this, MyViewModelFactory("settings")
        ).get(HomeViewModel::class.java)

        myViewModel.userMutableLiveData.observe(viewLifecycleOwner, userListUpdateObserver)

    }


    private val userListUpdateObserver: Observer<Array<String>?> =
        Observer { userArrayList ->
            for (s in userArrayList!!) {
                adapter!!.add(RecyclerSettingsItem(activity as MainActivity, s))
            }
        }

    companion object {

    }
}