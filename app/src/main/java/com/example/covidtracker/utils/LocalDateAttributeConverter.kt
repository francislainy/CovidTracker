package com.example.covidtracker.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.sql.Date
import java.time.LocalDate
import java.time.ZoneId.systemDefault

class Converters {
    @TypeConverter
    fun convertToDatabaseColumn(locDate: LocalDate?): Date? {
        return locDate?.let { Date.valueOf(locDate.toString()) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun convertToEntityAttribute(sqlDate: Date?): LocalDate? {
        val defaultZoneId = systemDefault()
        val instant = sqlDate?.toInstant()
        return instant?.atZone(defaultZoneId)?.toLocalDate()
    }
}