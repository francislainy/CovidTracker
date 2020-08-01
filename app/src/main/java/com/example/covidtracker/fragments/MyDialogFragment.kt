package com.example.covidtracker.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.covidtracker.model.ModelTest
import com.example.covidtracker.view_models.MyViewModelFactoryForHashMap
import com.example.covidtracker.view_models.TestViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_dialog.*


class MyDialogFragment : DialogFragment(), RecyclerDialogOptionsItem.AdapterListener {

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>
    private var viewModel: TestViewModel? = null


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


        rvOptions.layoutManager = LinearLayoutManager(activity)
        adapter = GroupAdapter()
        rvOptions.adapter = adapter


        viewModel = ViewModelProviders.of(activity as MainActivity).get(TestViewModel::class.java)
        val myViewModel = ViewModelProvider(
            this, MyViewModelFactoryForHashMap(arguments?.getString("headerText"))
        ).get(TestViewModel::class.java)

        myViewModel.userMutableLiveData.observe(this, userListUpdateObserver)


        ivClose.setOnClickListener {
            this.dismiss()
        }
    }


    private val userListUpdateObserver: Observer<Array<ModelTest>?> =
        Observer { userArrayList ->
            for (s in userArrayList!!) {
                adapter.add(
                    RecyclerDialogOptionsItem(
                        activity as MainActivity,
                        s.title,
                        s.selected!!,
                        this@MyDialogFragment
                    )
                )
            }
        }

    override fun onClickItem(id: Int, position: Int) {
        Log.i("clicked", "id: $id - position: $position" )
    }

}