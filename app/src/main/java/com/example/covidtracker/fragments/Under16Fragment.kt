package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.covidtracker.R
import com.example.covidtracker.utils.gone
import com.example.covidtracker.utils.visible
import kotlinx.android.synthetic.main.fragment_app_metrics.*
import kotlinx.android.synthetic.main.snippet_toolbar_plain.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class Under16Fragment : Fragment(R.layout.fragment_under16) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Under 16"
    }


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    requireActivity().finish()
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }


    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        snippedToolbar.visible()
        ivSettingsCog.gone()
        tvSettings.gone()

    }


}