package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidtracker.R
import com.example.covidtracker.view_models.Communicator
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_sender.*

class ContactTracingFragment : Fragment() {

    private var model: Communicator? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_contact_tracing, container, false)
        return inflater.inflate(R.layout.fragment_sender, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model= ViewModelProviders.of(activity as MainActivity).get(Communicator::class.java)

        navController = Navigation.findNavController(view)


        //listener onClick
        btnSend.setOnClickListener {view ->
            //set the message to share to another fragment
            model!!.setMsgCommunicator(txSendToFgm.text.toString())

            //Launch the data receiver fragment

            navController!!.navigate(R.id.action_contactTracingFragment_to_receiveFragment)

        }
    }





}