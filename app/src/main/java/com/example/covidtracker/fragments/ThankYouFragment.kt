package com.example.covidtracker.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_thank_you.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class ThankYouFragment : Fragment(R.layout.fragment_thank_you) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Thank you"

        val args: ThankYouFragmentArgs by navArgs()

        tvThankYouMessage.text = args.thankYouMessage
        tvAdvice.text = args.advice

        btnViewMyHistory.setOnClickListener(onClickButton)
        btnAdviceOnProtection.setOnClickListener(onClickButton)
    }


    private val onClickButton = View.OnClickListener {

        navController = Navigation.findNavController(requireView())



        when (it) {

            btnViewMyHistory -> {

                navController!!.navigate(R.id.action_thankYouFragment_to_checkInBottomFragment)
//                navController!!.popBackStack().and(true)
            }

            btnAdviceOnProtection -> {

                val url = "https://www2.hse.ie/coronavirus/"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
    }

}