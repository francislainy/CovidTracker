package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerTestItem
import com.example.covidtracker.model.ModelTest
import com.example.covidtracker.view_models.MyViewModelFactoryForHashMap
import com.example.covidtracker.view_models.TestViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_contact_tracing_bottom.*

class ContactTracingBottomFragment : Fragment(R.layout.fragment_contact_tracing_bottom) {

    private var adapter: GroupAdapter<GroupieViewHolder>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = GroupAdapter()
        rvTest.layoutManager = LinearLayoutManager(activity)
        rvTest.adapter = adapter


        val viewModel = ViewModelProviders.of(this, MyViewModelFactoryForHashMap("Choose your age range")).get(TestViewModel::class.java)

        viewModel.userMutableLiveData.observe(viewLifecycleOwner, userListUpdateObserver)

    }


    private val userListUpdateObserver: Observer<Array<ModelTest>?> =
        Observer { userArrayList ->
            for (s in userArrayList!!) {
                adapter!!.add(
                    RecyclerTestItem(
                        activity as MainActivity,
                        s.title,
                        s.selected.toString()
                    )
                )
            }
        }

}