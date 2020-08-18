package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class ContactTracingFragment : Fragment(R.layout.fragment_contact_tracing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Contact Tracing"

    }

}