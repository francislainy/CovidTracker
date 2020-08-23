package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_must_be_older16.*


class MustBeOlder16Fragment : Fragment(R.layout.fragment_must_be_older16) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        btnAbove16.setOnClickListener {
            navController!!.navigate(R.id.action_mustBeOlder16Fragment_to_getStartedFragment)
        }

        btnBelow16.setOnClickListener {
            navController!!.navigate(R.id.action_mustBeOlder16Fragment_to_under16Fragment)
        }

    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }


    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}


