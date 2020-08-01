package com.example.covidtracker.adapter_holders

import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rv_history_item_row.view.*
import kotlinx.android.synthetic.main.rv_test_item_row.view.*

private val LOG_TAG = RecyclerTestItem::class.java.canonicalName

class RecyclerTestItem(
    private val activity: MainActivity,
    private val text1: String?,
    private val text2: String?
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.apply {

            with(viewHolder.itemView) {

                headline.text = text1
                isHeadlineChecked.text = text2

                isHeadlineChecked.setOnClickListener {
                    isHeadlineChecked.text = "clicked"
                }

            }

        }
    }


    override fun getLayout() = R.layout.rv_test_item_row

}