package com.example.covidtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_covid_check_in.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class CovidCheckInFragment : Fragment(R.layout.fragment_covid_check_in) {

    var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "COVID Check-in"

        navController = Navigation.findNavController(view)


        // Listeners
        etYourAge.setOnClickListener(onClick)
        etYourCounty.setOnClickListener(onClick)
        etYourLocality.setOnClickListener(onClick)

    }

    private val onClick = View.OnClickListener {

        val destination: Int = R.id.action_covidCheckInFragment_to_my_dialog_fragment

        val message: String? = when (it.id) {
            R.id.etYourAge -> "Choose your age range"
            R.id.etYourCounty -> "Choose your county"
            R.id.etYourLocality -> "Choose your locality"
            else -> {
                ""
            }
        }


        val bundle = bundleOf("headerText" to message)
        navController = Navigation.findNavController(it)

        navController!!.navigate(
            destination,
            bundle
        )

    }

    companion object {

    }
}
