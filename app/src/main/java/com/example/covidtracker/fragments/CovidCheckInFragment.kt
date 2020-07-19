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

        fun newInstance() =
            CovidCheckInFragment()
    }
}
