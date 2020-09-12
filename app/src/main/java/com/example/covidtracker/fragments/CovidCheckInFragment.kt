package com.example.covidtracker.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.model.ModelDialogOption
import com.example.covidtracker.view_models.MainViewModel
import kotlinx.android.synthetic.main.fragment_covid_check_in.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*


class CovidCheckInFragment : Fragment(R.layout.fragment_covid_check_in) {

    var navController: NavController? = null
    private val model: MainViewModel by viewModels(
        { requireParentFragment() }
    )


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "COVID Check-in"

        navController = Navigation.findNavController(view)


        // Listeners
        etYourAge.setOnClickListener(onClick)
        etYourCounty.setOnClickListener(onClick)
        etYourLocality.setOnClickListener(onClick)

        rbFemale.setOnClickListener(onClickRb)
        rbMale.setOnClickListener(onClickRb)
        rbPreferNotToSay.setOnClickListener(onClickRb)

        model.userMutableLiveData.observe(viewLifecycleOwner, Observer<Any?> { list ->
            if (list != null)

                (list as Iterable<*>).map {

                    if ((it as ModelDialogOption).selected == true) {
                        etYourAge.text = it.title

                    }

                }

        })

        model.updateList()


    }


    private val onClick = View.OnClickListener {

        val destination: Int = R.id.action_covidCheckInFragment_to_my_dialog_fragment

        val message: String? = when (it.id) {
            R.id.etYourAge -> "Choose your age range"
            R.id.etYourCounty -> "Choose your county"
            R.id.etYourLocality -> "Choose your locality"
            else -> {
                ""
            }
        }


        val bundle = bundleOf("headerText" to message)
        navController = Navigation.findNavController(it)

        navController!!.navigate(
            destination,
            bundle
        )

    }


    @RequiresApi(Build.VERSION_CODES.M)
    private val onClickRb = View.OnClickListener {


        val list = listOf<Button>(rbFemale, rbMale, rbPreferNotToSay)

        for (i in list) {
            i.setTextColor(resources.getColor(R.color.text))
            i.background.setTint(ContextCompat.getColor(activity as MainActivity, R.color.greyEee))
        }

        if ((it as AppCompatRadioButton).isChecked) {

            it.setTextColor(resources.getColor(R.color.white))
            it.background.setTint(ContextCompat.getColor(activity as MainActivity, R.color.shadow))

        }
    }

}
