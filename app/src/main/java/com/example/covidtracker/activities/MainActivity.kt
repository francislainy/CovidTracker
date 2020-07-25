package com.example.covidtracker.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.covidtracker.R
import com.example.covidtracker.utils.gone
import com.example.covidtracker.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*
import java.util.*


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

        val appBarConfiguration = AppBarConfiguration(bottomNav.menu)
        setupActionBarWithNavController(navController, appBarConfiguration)


        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {

                R.id.updatesFragment, R.id.contactTracingBottomFragment, R.id.checkInBottomFragment -> {
                    ivSettingsCog.visible()
                }

                else -> {
                    ivSettingsCog.gone()
                }
            }

        }


        bottomNav.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.share -> {
                    shareViaWhatsApp()
                    false
                }
                else -> {
                    true
                }
            }

        }



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
                R.id.share -> {
                    shareViaWhatsApp()
                }

            }

        }


    }


    private fun shareViaWhatsApp(): Unit {
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.setPackage("com.whatsapp")
        whatsappIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Application of social rating share with your friend"
        )
        try {
            Objects.requireNonNull(this).startActivity(whatsappIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")
                )
            )
        }

    }

}
