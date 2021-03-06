package com.example.covidtracker.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.adapter_holders.RecyclerDialogOptionsItem
import com.example.covidtracker.view_models.MainViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_dialog.*


class MyDialogFragment : DialogFragment(), RecyclerDialogOptionsItem.AdapterListener {

    private val model: MainViewModel by activityViewModels()

    private lateinit var adapter: GroupAdapter<GroupieViewHolder>
    var selectedPosition = -1

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


        ivClose.setOnClickListener {

            this.dismiss()
        }


        initViewModel()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        model.flushItems()
    }

    private fun initViewModel() {

        model.userMutableLiveData.observe(this, Observer { list ->
            for (i in list!!) {
                adapter.add(
                    RecyclerDialogOptionsItem(
                        this@MyDialogFragment,
                        i,
                        this@MyDialogFragment
                    )
                )
            }

        })

    }


    override fun onClickItem(position: Int) {

        selectedPosition = position
        adapter.notifyDataSetChanged()

        model.updateItem(position)

        Log.i("clicked", "position: $position")
    }

}