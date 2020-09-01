package com.example.covidtracker.model

data class TodayTotals(
    val todayTotals: TodayTotals_
)

data class TodayTotals_(
    val totalCheckIn: Int,
    val totalFeelingGoodPercentage: Int,
    val totalSomeSymptomsPercentage: Int
)