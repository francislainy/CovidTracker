package com.example.covidtracker.adapter_holders

import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rv_county_item_row.view.*
import kotlinx.android.synthetic.main.rv_history_item_row.view.*

private val LOG_TAG = RecyclerCountyItem::class.java.canonicalName

class RecyclerCountyItem(
    private val activity: MainActivity,
    private val title: String?,
    private val value: String?
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.apply {

            with(viewHolder.itemView) {

                tvCountyName.text = title
                tvValue.text = value

            }

        }
    }


    override fun getLayout() = R.layout.rv_county_item_row

}