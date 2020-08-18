package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_app_metrics.*
import kotlinx.android.synthetic.main.fragment_leave.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class LeaveFragment : Fragment(R.layout.fragment_leave) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Leave"

        navController = Navigation.findNavController(view)

        tvReadPrivacy.setOnClickListener {
            navController!!.navigate(R.id.action_leaveFragment_to_dataProtectionFragment)
        }
    }

}