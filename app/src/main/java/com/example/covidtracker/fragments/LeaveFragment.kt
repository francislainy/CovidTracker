package com.example.covidtracker.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_app_metrics.*
import kotlinx.android.synthetic.main.fragment_leave.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class LeaveFragment : Fragment(R.layout.fragment_leave) {

    private var navController: NavController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "Leave"

        navController = Navigation.findNavController(view)
        val bundle = bundleOf("state" to "leave")

        tvReadPrivacy.setOnClickListener {
            navController!!.navigate(R.id.action_leaveFragment_to_dataProtectionFragment, bundle)
        }

        btnLeave.setOnClickListener {
            val builder = AlertDialog.Builder(requireActivity(), R.style.AlertDialogCustom)
            builder
                .setTitle("Leave")
                .setMessage("Are you sure you want to leave?")
                .setCancelable(false)
                .setPositiveButton("CONFIRM") { dialog, id ->
                    val preference = (activity as MainActivity).getSharedPreferences(
                        resources.getString(R.string.app_name), Context.MODE_PRIVATE
                    )
                    val editor = preference.edit()
                    editor.putBoolean(getString(R.string.isLoggedIn), false)
                    editor.apply()
                    navController!!.navigate(R.id.action_leaveFragment_to_mustBeOlder16Fragment, bundle)
                }
                .setNegativeButton("CANCEL") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

}