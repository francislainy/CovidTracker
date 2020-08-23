package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.covidtracker.R
import com.example.covidtracker.utils.gone
import com.example.covidtracker.utils.visible
import kotlinx.android.synthetic.main.fragment_app_metrics.*
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class AppMetricsFragment : Fragment(R.layout.fragment_app_metrics) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "App Metrics"

        navController = Navigation.findNavController(view)

        val args: AppMetricsFragmentArgs by navArgs()

        if (args.state == "start") {

            tvShareAppStatistics.gone()
            switchStatistics.gone()

        } else {

            clConsent.gone()
        }


        btnConsent.setOnClickListener {
            navController!!.navigate(R.id.action_appMetricsFragment_to_updatesFragment)
        }

        val bundle = bundleOf("section" to "Data Protection Information Notice")
        tvLinkDataProtection.setOnClickListener {
            navController!!.navigate(
                R.id.action_appMetricsFragment_to_dataProtectionFragment,
                bundle
            )
        }
    }


    override fun onResume() {
        super.onResume()

        val args: AppMetricsFragmentArgs by navArgs()

        if (args.state == "start") {

            (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
            snippedToolbar.visible()
            ivSettingsCog.gone()
            tvSettings.gone()

        } else {
            (activity as AppCompatActivity?)!!.supportActionBar!!.show()
            snippedToolbar.gone()
        }
    }
}