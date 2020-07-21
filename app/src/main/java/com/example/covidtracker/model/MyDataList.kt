package com.example.covidtracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.covidtracker.Converters
import java.time.LocalDate

@Entity(tableName = "mydatalist")
data class MyDataList(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "status")
    var status: String? = null,

//    @TypeConverters(Converters::class)
    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "has_replied_today")
    var hasRepliedToday: Boolean? = null

)