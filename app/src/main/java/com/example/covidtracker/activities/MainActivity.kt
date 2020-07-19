package com.example.covidtracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val navController = findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(toolbar, navController)
        NavigationUI.setupWithNavController(bottomNav, navController)
        bottomNav.setBackgroundColor(ContextCompat.getColor(this, R.color.white))


        ivSettingsCog.setOnClickListener {

            when (navController.currentDestination?.id) {
                R.id.updatesFragment -> {
                    navController.navigate(R.id.action_updatesFragment_to_settingsFragment)
                }
                R.id.contactTracingFragment -> {
                    navController.navigate(R.id.action_contactTracingFragment_to_settingsFragment)
                }
                R.id.covidCheckInFragment -> {
                    navController.navigate(R.id.action_covidCheckInFragment_to_settingsFragment)
                }

            }

        }

    }

}
