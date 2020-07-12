package com.example.covidtracker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtracker.R
import com.example.covidtracker.fragments.UpdatesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = UpdatesFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }

    }
}