package com.example.covidtracker.adapter_holders

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.fragment_covid_check_in.view.*
import kotlinx.android.synthetic.main.rv_settings_item_row.view.*

private val LOG_TAG = RecyclerSettingsItem::class.java.canonicalName

class RecyclerSettingsItem(
    private val activity: MainActivity,
    private val settingItem: String
) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        var navController: NavController? = null

        viewHolder.apply {

            with(viewHolder.itemView) {

                tvTitle.text = settingItem

                itemView.setOnClickListener {

                    navController = Navigation.findNavController(activity, R.id.nav_host_fragment)

                    when (settingItem) {
                        "Contact Tracing" -> navController!!.navigate(R.id.action_settingsFragment_to_contactTracingFragment)
                        "COVID Check-In" -> navController!!.navigate(R.id.action_settingsFragment_to_covidCheckInFragment)
                    }

                }

            }

        }
    }


    override fun getLayout() = R.layout.rv_settings_item_row

}