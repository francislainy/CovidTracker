package com.example.covidtracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covidtracker.model.MyDataList


@Database(entities = [MyDataList::class], version = 6, exportSchema = false)
abstract class DataRoomDbase : RoomDatabase() {

    abstract fun dataDAO(): MyDao?

    companion object {
        private var INSTANCE: DataRoomDbase? = null
        fun getDatabase(context: Context): DataRoomDbase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DataRoomDbase::class.java, DataRoomDbase::class.java.name
                ) //if you want create db only in memory, not in file
                    //Room.inMemoryDatabaseBuilder
                    //(context.getApplicationContext(), DataRoomDbase.class)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}