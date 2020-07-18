package com.example.covidtracker.adapter_holders

import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rv_settings_item_row.view.*

private val LOG_TAG = RecyclerDialogOptionsItem::class.java.canonicalName

class RecyclerDialogOptionsItem(
    private val activity: MainActivity,
    private val optionsItem: String
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.apply {

            with(viewHolder.itemView) {

                tvTitle.text = optionsItem

            }

        }
    }


    override fun getLayout() = R.layout.rv_options_item_row

}