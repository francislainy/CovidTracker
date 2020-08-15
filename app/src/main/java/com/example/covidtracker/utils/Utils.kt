package com.example.covidtracker.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.covidtracker.activities.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        private val dateFormat = SimpleDateFormat("d MMMM", Locale.getDefault())

        fun formatDate(date: Date): String {
            val oi =
                getOrdinalIndicator(
                    date
                )
            return dateFormat.apply {
                applyPattern("d'$oi' MMMM")
            }.format(date)
        }

        private fun getOrdinalIndicator(date: Date): String {
            val day = newCalendar(
                date
            ).get(Calendar.DAY_OF_MONTH)

            if (day == 11 || day == 12 || day == 13) {
                return "th"
            }

            return when (day % 10) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }
        }

        private fun newCalendar(date: Date): Calendar {
            return Calendar.getInstance().apply {
                time = date
            }
        }


        fun shareViaWhatsApp(activity: MainActivity) {
            val whatsAppIntent = Intent(Intent.ACTION_SEND)
            whatsAppIntent.type = "text/plain"
            whatsAppIntent.setPackage("com.whatsapp")
            whatsAppIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Application of social rating share with your friend"
            )
            try {
                activity.startActivity(whatsAppIntent)
            } catch (ex: ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")
                    )
                )
            }

        }

    }

}