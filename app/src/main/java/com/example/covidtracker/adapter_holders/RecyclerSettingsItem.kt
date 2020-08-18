package com.example.covidtracker.adapter_holders

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.covidtracker.R
import com.example.covidtracker.activities.MainActivity
import com.example.covidtracker.fragments.DataProtectionFragment
import com.example.covidtracker.fragments.DataProtectionFragmentDirections
import com.example.covidtracker.fragments.SettingsFragmentDirections
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

                setOnClickListener {

                    navController = Navigation.findNavController(activity, R.id.nav_host_fragment)

                    when (settingItem) {
                        "Contact Tracing" -> navController!!.navigate(R.id.action_settingsFragment_to_contactTracingFragment)
                        "COVID Check-In" -> navController!!.navigate(R.id.action_settingsFragment_to_covidCheckInFragment)
//                        "Data Protection Information Notice" -> navController!!.navigate(R.id.action_settingsFragment_to_dataProtectionFragment)
                        "Data Protection Information Notice" -> {
                            val bundle = bundleOf("section" to "Data Protection Information Notice")
                            navController!!.navigate(
                                R.id.action_settingsFragment_to_dataProtectionFragment,
                                bundle
                            )
                        }
                        "Terms & Conditions" -> {
                            val bundle = bundleOf("section" to "Terms & Conditions")
                            navController!!.navigate(
                                R.id.action_settingsFragment_to_dataProtectionFragment,
                                bundle
                            )
                        }
                        "Leave" -> {
                            navController!!.navigate(R.id.action_settingsFragment_to_leaveFragment)
                        }
                        "App Metrics" -> {
                            navController!!.navigate(R.id.action_settingsFragment_to_appMetricsFragment)
                        }
                    }

                }

            }

        }
    }


    override fun getLayout() = R.layout.rv_settings_item_row

}