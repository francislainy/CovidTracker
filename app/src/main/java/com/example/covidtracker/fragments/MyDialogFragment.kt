package com.example.covidtracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.adapter_holders.RecyclerDialogOptionsItem
import com.example.covidtracker.adapter_holders.RecyclerSettingsItem
import com.example.covidtracker.view_models.HomeViewModel
import com.example.covidtracker.view_models.MyViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_dialog.*


class MyDialogFragment : DialogFragment() {

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>
    private var viewModel: HomeViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_Custom)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvChoose.text = arguments?.getString("headerText")


        rvOptions.layoutManager =  LinearLayoutManager(activity)
        adapter = GroupAdapter()
        rvOptions.adapter = adapter


        viewModel = ViewModelProviders.of(activity as MainActivity).get(HomeViewModel::class.java)
        val myViewModel = ViewModelProvider(
            this, MyViewModelFactory(arguments?.getString("headerText"))
        ).get(HomeViewModel::class.java)

        myViewModel.userMutableLiveData.observe(viewLifecycleOwner, userListUpdateObserver)


        ivClose.setOnClickListener {
            this.dismiss()
        }
    }


     private val userListUpdateObserver: Observer<Array<String>?> =
        Observer { userArrayList ->
            for (s in userArrayList!!) {
                adapter.add(RecyclerDialogOptionsItem(activity as MainActivity, s))
            }
        }

}