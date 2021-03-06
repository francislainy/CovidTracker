package com.example.covidtracker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.covidtracker.R
import com.example.covidtracker.utils.Utils.Companion.shareViaWhatsApp
import com.example.covidtracker.utils.gone
import com.example.covidtracker.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*


class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)


        navController = findNavController(this, R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController!!)
        NavigationUI.setupWithNavController(toolbar, navController!!)
        NavigationUI.setupWithNavController(bottomNav, navController!!)
        bottomNav.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

//        val appBarConfiguration = AppBarConfiguration(bottomNav.menu)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.updatesFragment,
                R.id.contactTracingBottomFragment,
                R.id.checkInBottomFragment,
                R.id.howYouFeelingFragment,
                R.id.thankYouFragment,
                R.id.notWellSymptomsFragment
            )
        )
        setupActionBarWithNavController(navController!!, appBarConfiguration)


        navController!!.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {

                R.id.updatesFragment, R.id.contactTracingBottomFragment, R.id.checkInBottomFragment, R.id.howYouFeelingFragment, R.id.thankYouFragment, R.id.notWellSymptomsFragment -> {
                    ivSettingsCog.visible()
                    bottomNav.visible()
                }

                else -> {
                    ivSettingsCog.gone()
                    tvSettings.gone()
                    bottomNav.gone()
                }
            }
        }


        bottomNav.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.share -> {
                    shareViaWhatsApp(this@MainActivity)
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(it, navController!!)
                }
            }

            true

        }


        ivSettingsCog.setOnClickListener {

            when (navController!!.currentDestination?.id) {
                R.id.updatesFragment -> {
                    navController!!.navigate(R.id.action_updatesFragment_to_settingsFragment)
                }
                R.id.contactTracingBottomFragment -> {
                    navController!!.navigate(R.id.action_contactTracingBottomFragment_to_settingsFragment)
                }
                R.id.checkInBottomFragment -> {
                    navController!!.navigate(R.id.action_checkInBottomFragment_to_settingsFragment)
                }
                R.id.howYouFeelingFragment -> {
                    navController!!.navigate(R.id.action_howYouFeelingFragment_to_settingsFragment)
                }
                R.id.thankYouFragment -> {
                    navController!!.navigate(R.id.action_thankYouFragment_to_settingsFragment)
                }
                R.id.notWellSymptomsFragment -> {
                    navController!!.navigate(R.id.action_notWellSymptomsFragment_to_settingsFragment)
                }

                R.id.share -> {
                    shareViaWhatsApp(this@MainActivity)
                }

            }

        }

    }


}
