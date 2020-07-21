package com.example.covidtracker

import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        private val dateFormat = SimpleDateFormat("d MMMM", Locale.getDefault())

        fun formatDate(date: Date): String {
            val oi = getOrdinalIndicator(date)
            return dateFormat.apply {
                applyPattern("d'$oi' MMMM")
            }.format(date)
        }

        private fun getOrdinalIndicator(date: Date): String {
            val day = newCalendar(date).get(Calendar.DAY_OF_MONTH)

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
    }
}