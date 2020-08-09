package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covidtracker.R
import com.example.covidtracker.utils.gone
import kotlinx.android.synthetic.main.how_you_feeling_layout.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class HowYouFeelingFragment : Fragment(R.layout.fragment_how_you_feeling) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "COVID Check-In"

        tvWelcomeBack.gone()
        ivClose.gone()

        btnImGood.setOnClickListener(onClickButton)
        btnImNotWell.setOnClickListener(onClickButton)
    }


    private val onClickButton = View.OnClickListener {

        when(it) {

            btnImGood -> {
                Toast.makeText(activity, "Well", Toast.LENGTH_SHORT).show()
            }
            btnImNotWell -> {
                Toast.makeText(activity, "Not well", Toast.LENGTH_SHORT).show()
            }

            
        }


    }

}