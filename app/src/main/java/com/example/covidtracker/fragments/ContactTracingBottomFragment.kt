package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerTestItem
import com.example.covidtracker.model.ModelTest
import com.example.covidtracker.utils.Utils
import com.example.covidtracker.utils.Utils.Companion.shareViaWhatsApp
import com.example.covidtracker.view_models.MyViewModelFactoryForHashMap
import com.example.covidtracker.view_models.TestViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_contact_tracing_bottom.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class ContactTracingBottomFragment : Fragment(R.layout.fragment_contact_tracing_bottom) {

    var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        tvHeader.text = "Contact Tracing"

        btnShare.setOnClickListener { shareViaWhatsApp(activity as MainActivity) }
        closeContactInformationCard.setOnClickListener(onClickCard)
        uploadIdCard.setOnClickListener(onClickCard)


    }

    private val onClickCard = View.OnClickListener {

        when (it) {
            closeContactInformationCard -> navController!!.navigate(R.id.action_contactTracingBottomFragment_to_closeContactFragment)
            uploadIdCard -> navController!!.navigate(R.id.action_contactTracingBottomFragment_to_uploadIdFragment)
        }


    }

}