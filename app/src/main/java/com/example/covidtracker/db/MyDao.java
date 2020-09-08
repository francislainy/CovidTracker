package com.example.covidtracker.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.covidtracker.model.MyDataList;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    void addData(MyDataList mydatalist);

    @Query("select * from mydatalist order by id DESC")
    LiveData<List<MyDataList>> getMyData();

    @Query("delete from mydatalist")
    void deleteAll();

    @Delete
    void delete(MyDataList mydatalist);

    @Update
    void update(MyDataList mydatalist);

}
