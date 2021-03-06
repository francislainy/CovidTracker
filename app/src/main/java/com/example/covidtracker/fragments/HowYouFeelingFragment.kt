package com.example.covidtracker.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.db.DataRoomDbase
import com.example.covidtracker.model.MyDataList
import com.example.covidtracker.utils.gone
import kotlinx.android.synthetic.main.how_you_feeling_layout.*
import kotlinx.android.synthetic.main.title_and_progress_bar.*
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class HowYouFeelingFragment : Fragment(R.layout.fragment_how_you_feeling) {

    private var navController: NavController? = null
    private var myDatabase: DataRoomDbase? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHeader.text = "COVID Check-In"

        tvWelcomeBack.gone()
        ivClose.gone()

        btnImGood.setOnClickListener(onClickButton)
        btnImNotWell.setOnClickListener(onClickButton)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private val onClickButton = View.OnClickListener {

        val preference = (activity as MainActivity).getSharedPreferences(
            resources.getString(R.string.app_name), Context.MODE_PRIVATE
        )
        val editor = preference.edit()
        val date = Date()
        val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        editor.putBoolean(getString(R.string.hasCheckedPreference), true)
        editor.putString(getString(R.string.dateCheckedPreference), localDate.toString())
        editor.apply()

        val myDataList = MyDataList()
        myDataList.date = java.util.Date().toString()
        myDataList.hasRepliedToday = true
        it?.isEnabled = false

        navController = Navigation.findNavController(requireView())

        myDatabase = DataRoomDbase.getDatabase(activity as MainActivity)

        when (it) {
            btnImGood -> {
                myDataList.status = "Good"
                navController!!.navigate(R.id.action_howYouFeelingFragment_to_checkInBottomFragment)
            }
            btnImNotWell -> {
                myDataList.status = "Bad"
                navController!!.navigate(R.id.action_howYouFeelingFragment_to_notWellSymptomsFragment)
            }
        }



        myDatabase?.dataDAO()?.addData(myDataList)

    }

}