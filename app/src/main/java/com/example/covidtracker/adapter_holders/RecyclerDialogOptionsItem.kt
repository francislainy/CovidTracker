package com.example.covidtracker.adapter_holders

import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.fragments.MyDialogFragment
import com.example.covidtracker.utils.invisible
import com.example.covidtracker.utils.visible
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rv_options_item_row.view.*
import kotlinx.android.synthetic.main.rv_settings_item_row.view.tvTitle

private val LOG_TAG = RecyclerDialogOptionsItem::class.java.canonicalName

class RecyclerDialogOptionsItem(
    private val fragment: MyDialogFragment,
    private val title: String?,
    private var selected: Boolean,
    private val adapterListener: AdapterListener
) : Item<GroupieViewHolder>() {

    companion object {
        var clickListener: AdapterListener? = null
    }


    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.apply {

            with(viewHolder.itemView) {

                tvTitle.text = title

                clickListener = adapterListener

                if (fragment.selected_position == position) {
                    ivChecked.visible()

                } else {
                    ivChecked.invisible()
                }

                itemView.setOnClickListener {
                    clickListener?.onClickItem(adapterPosition)

                }

            }

        }

    }


    override fun getLayout() = R.layout.rv_options_item_row


    interface AdapterListener {
        fun onClickItem(position: Int)
    }
}