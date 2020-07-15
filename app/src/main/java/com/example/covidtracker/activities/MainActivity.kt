package com.example.covidtracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(this, R.id.nav_host_fragment)

        ivSettingsCog.setOnClickListener {

            when (navController.currentDestination?.id) {
                R.id.updatesFragment -> {
                    navController.navigate(R.id.action_updatesFragment_to_settingsFragment)
                }
                R.id.contactTracingFragment -> {
                    navController.navigate(R.id.action_contactTracingFragment_to_settingsFragment)
                }
            }

        }

    }

}
