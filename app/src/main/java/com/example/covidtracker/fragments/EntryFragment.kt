package com.example.covidtracker.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity

class EntryFragment : Fragment(R.layout.fragment_must_be_older16) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val preference = (activity as MainActivity).getSharedPreferences(
            resources.getString(R.string.app_name), Context.MODE_PRIVATE
        )

        val isLoggedIn = preference.getBoolean(getString(R.string.isLoggedIn), false)
        when (isLoggedIn) {

            true -> navController!!.navigate(R.id.action_entryFragment_to_updatesFragment)
            false -> navController!!.navigate(R.id.action_entryFragment_to_mustBeOlder16Fragment)
        }
    }
}