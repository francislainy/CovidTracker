package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.model.QA
import kotlinx.android.synthetic.main.fragment_not_well_symptoms.*
import kotlinx.android.synthetic.main.fragment_updates.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*

class NotWellSymptomsFragment : Fragment(R.layout.fragment_not_well_symptoms) {

    private var questionsList: List<QA>? = null
    private var index = 0
    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "COVID Check-In"

        questionsList = listOf(
            QA(
                "Do you have a fever – a high temperature over 38 degrees Celsius?",
                "Symptoms of a fever can include flushed cheeks, feeling tired and being warm or hot to touch."
            ),
            QA(
                "Do you have any type of cough?",
                ""
            ),
            QA(
                "Do you have any difficulty breathing?",
                "This might be like panting or feeling like you can't fill your lungs."
            ),
            QA(
                "Do you have any loss of sense of taste or smell?",
                "This might mean you can't taste or smell anything, or things taste or smell different to normal."
            )
        )

        btnNoSymptoms.setOnClickListener(onClickAnswer)
        btnYesSymptoms.setOnClickListener(onClickAnswer)

    }


    private val onClickAnswer = View.OnClickListener {

        index++
        if (index < questionsList!!.size) {

            tvDoYouHaveSymptomsHeader.text = questionsList?.get(index)?.questionTitle
            tvSymptomsDescription.text = questionsList?.get(index)?.questionDescription

            when (it) {
                btnNoSymptoms -> {
                    questionsList?.get(index)?.questionAnswer = "No"
                }
                btnYesSymptoms -> {
                    questionsList?.get(index)?.questionAnswer = "Yes"
                }
            }

        } else {

            // todo: introduce logic for only if one item at least as shown them it shows a thank you page but with different text,
            // otherwise same page as before.

            navController = Navigation.findNavController(requireView())

            navController!!.navigate(R.id.action_notWellSymptomsFragment_to_thankYouFragment)

        }
    }
}