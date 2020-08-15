package com.example.covidtracker.adapter_holders

import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rv_things_to_protect_item_row.view.*

private val LOG_TAG = RecyclerThingsToProtectItem::class.java.canonicalName

class RecyclerThingsToProtectItem(
    private val activity: MainActivity,
    private val itemNumber: String?,
    private val itemDescription: String?
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.apply {

            with(viewHolder.itemView) {

                tvItemNumber.text = (position + 1).toString()
                tvItemDescription.text = itemDescription
            }

        }
    }


    override fun getLayout() = R.layout.rv_things_to_protect_item_row

}