package com.example.covidtracker.adapter_holders

import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rv_history_item_row.view.*
import kotlinx.android.synthetic.main.rv_settings_item_row.view.*

private val LOG_TAG = RecyclerHistoryItem::class.java.canonicalName

class RecyclerHistoryItem(
    private val activity: MainActivity,
    private val status: String,
    private val date: String
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.apply {

            with(viewHolder.itemView) {

                tvSymptoms.text = status
                tvDate.text = date

            }

        }
    }


    override fun getLayout() = R.layout.rv_history_item_row

}