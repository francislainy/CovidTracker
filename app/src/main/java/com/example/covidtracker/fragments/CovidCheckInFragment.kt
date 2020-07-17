package com.example.covidtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        headerHolder.setOnClickListener {
            navController!!.navigate(R.id.action_covidCheckInFragment_to_my_dialog_fragment)
        }

    }

    companion object {

        fun newInstance() =
            CovidCheckInFragment()
    }
}
