package com.example.covidtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_covid_check_in.*
import kotlinx.android.synthetic.main.todays_fight_layout.*


class CovidCheckInFragment : Fragment() {


    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_covid_check_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        // Listeners
        etYourAge.setOnClickListener(onClick)
        etYourCounty.setOnClickListener(onClick)
        etYourLocality.setOnClickListener(onClick)

    }

    private val onClick = View.OnClickListener {

        val message: String?
        val destination: Int?
        when (it.id) {
            R.id.etYourAge -> {

                message = "Choose your age range"
                destination = R.id.action_covidCheckInFragment_to_my_dialog_fragment
            }

            R.id.etYourCounty -> {

                message = "Choose your county"
                destination = R.id.action_covidCheckInFragment_to_my_dialog_fragment
            }

            R.id.etYourLocality -> {

                message = "Choose your locality"
                destination = R.id.action_covidCheckInFragment_to_my_dialog_fragment
            }

            else -> {
                message = null
                destination = null
            }
        }


        val bundle = bundleOf("headerText" to message)
        navController!!.navigate(
            destination!!,
            bundle
        )

    }

    companion object {

        fun newInstance() =
            CovidCheckInFragment()
    }
}
