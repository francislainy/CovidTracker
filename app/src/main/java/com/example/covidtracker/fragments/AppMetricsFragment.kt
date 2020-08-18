package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_app_metrics.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class AppMetricsFragment : Fragment(R.layout.fragment_app_metrics) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "App Metrics"

        navController = Navigation.findNavController(view)

        tvLinkDataProtection.setOnClickListener {
            navController!!.navigate(R.id.action_appMetricsFragment_to_dataProtectionFragment)
        }
    }
}