package com.example.covidtracker.adapter_holders

import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.fragments.ContactTracingFragment
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.rv_settings_item_row.view.*

private val LOG_TAG = RecyclerSettingsItem::class.java.canonicalName

class RecyclerSettingsItem(
    private val activity: MainActivity,
    private val settingItem: String
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.apply {

            with(viewHolder.itemView) {

                tvTitle.text = settingItem

                itemView.setOnClickListener {
                    val fragment = ContactTracingFragment.newInstance()
                    activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                }

            }

        }
    }


    override fun getLayout() = R.layout.rv_settings_item_row

}