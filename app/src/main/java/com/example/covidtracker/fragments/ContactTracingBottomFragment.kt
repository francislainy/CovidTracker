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
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class ContactTracingBottomFragment : Fragment(R.layout.fragment_contact_tracing_bottom) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Contact Tracing"
    }

}