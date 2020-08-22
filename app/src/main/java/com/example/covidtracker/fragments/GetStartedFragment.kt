package com.example.covidtracker.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_get_started.*


class GetStartedFragment : Fragment(R.layout.fragment_get_started) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ss = SpannableString(tvIfYouGetStarted.text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                val bundle =
                    bundleOf("section" to "Terms & Conditions")
                navController!!.navigate(R.id.action_getStartedFragment_to_dataProtectionFragment, bundle)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }
        ss.setSpan(clickableSpan, 46, 65, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        tvIfYouGetStarted.text = ss
        tvIfYouGetStarted.movementMethod = LinkMovementMethod.getInstance()
        tvIfYouGetStarted.highlightColor = Color.TRANSPARENT


        navController = Navigation.findNavController(view)
        val bundle =
            bundleOf("section" to "Your data", "state" to "start")

        btnGetStarted.setOnClickListener {
            navController!!.navigate(R.id.action_getStartedFragment_to_dataProtectionFragment, bundle)
        }


    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

}