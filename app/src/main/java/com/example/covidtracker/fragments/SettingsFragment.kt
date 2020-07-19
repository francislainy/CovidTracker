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
import com.example.covidtracker.view_models.Communicator
import com.example.covidtracker.view_models.HomeViewModel
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

//        adapter = GroupAdapter()


        val list = arrayOf(
            "Contact Tracing",
            "COVID Check-In",
            "Data Protection Information Notice",
            "Terms & Conditions",
            "App Metrics",
            "Leave"
        )

//        for (s in list) {
//            adapter.add(RecyclerSettingsItem(activity as MainActivity, s))
//        }


//        rvSettings.adapter = adapter

        viewModel= ViewModelProviders.of(activity as MainActivity).get(HomeViewModel::class.java)

        viewModel?.userMutableLiveData?.observe(viewLifecycleOwner, userListUpdateObserver)




        val decoration = DividerItemDecoration(
            activity as MainActivity,
            HORIZONTAL
        )
        rvSettings.addItemDecoration(decoration)
    }


    private val userListUpdateObserver: Observer<ArrayList<String?>?> =
        object : Observer<ArrayList<String?>?> {
            override fun onChanged(userArrayList: ArrayList<String?>?) {

                adapter = GroupAdapter()

                for (s in userArrayList!!) {
                    adapter!!.add(RecyclerSettingsItem(activity as MainActivity, s!!))
                }


                rvSettings.layoutManager = LinearLayoutManager(requireActivity())
                rvSettings.adapter = adapter
            }
        }

    companion object {
        fun newInstance() =
            SettingsFragment()
    }
}