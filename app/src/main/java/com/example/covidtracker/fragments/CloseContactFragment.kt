package com.example.covidtracker.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerThingsToProtectItem
import com.example.covidtracker.model.ModelDialogOption
import com.example.covidtracker.view_models.MyViewModelFactoryForHashMap
import com.example.covidtracker.view_models.TestViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_close_contact.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class CloseContactFragment : Fragment(R.layout.fragment_close_contact) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null
    private var viewModel: TestViewModel? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Close contact information"


        adapter = GroupAdapter()
        rvThingsToProtect.layoutManager = LinearLayoutManager(activity)
        rvThingsToProtect.adapter = adapter

        viewModel = ViewModelProviders.of(activity as MainActivity).get(TestViewModel::class.java)
        val myViewModel = ViewModelProvider(
            this, MyViewModelFactoryForHashMap("things_to_protect")
        ).get(TestViewModel::class.java)

        myViewModel.userMutableLiveData.observe(viewLifecycleOwner, userListUpdateObserver)


        btnGoToHse.setOnClickListener { goToHse() }
    }


    private fun goToHse() {
        val url = "https://www2.hse.ie/coronavirus/"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


    private val userListUpdateObserver: Observer<Array<ModelDialogOption>?> =
        Observer { userArrayList ->
            for (s in userArrayList!!) {
                adapter!!.add(
                    RecyclerThingsToProtectItem(
                        activity as MainActivity,
                        s.title
                    )
                )
            }
        }


}