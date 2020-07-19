package com.example.covidtracker.fragments

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerSettingsItem
import com.example.covidtracker.view_models.HomeViewModel
import com.example.covidtracker.view_models.MyViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null
    private var viewModel: HomeViewModel? = null

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
        rvSettings.adapter = adapter

        val decoration = DividerItemDecoration(
            activity as MainActivity,
            HORIZONTAL
        )
        rvSettings.addItemDecoration(decoration)


        viewModel = ViewModelProviders.of(activity as MainActivity).get(HomeViewModel::class.java)

        val myViewModel = ViewModelProvider(
            this, MyViewModelFactory("settings")
        ).get(HomeViewModel::class.java)

        myViewModel.userMutableLiveData.observe(viewLifecycleOwner, userListUpdateObserver)

    }


    val userListUpdateObserver: Observer<Array<String>?> =
        Observer { userArrayList ->
            for (s in userArrayList!!) {
                adapter!!.add(RecyclerSettingsItem(activity as MainActivity, s))
            }
        }

    companion object {
        fun newInstance() =
            SettingsFragment()
    }
}