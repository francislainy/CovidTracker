package com.example.covidtracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.covidtracker.R

import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.view_models.Communicator

class ReceiveFragment: Fragment(R.layout.fragment_receive) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn=view.findViewById<View>(R.id.btnBack) as Button
        val txt=view.findViewById<View>(R.id.txreceiver) as TextView

        val model= ViewModelProviders.of(activity as MainActivity).get(Communicator::class.java)

        model.message.observe(viewLifecycleOwner, object : Observer<Any> {
            override fun onChanged(o: Any?) {
                txt.text = o!!.toString()
            }
        })
        btn.setOnClickListener { view ->
            //write some code here
        }
    }

}