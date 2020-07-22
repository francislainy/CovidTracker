package com.example.covidtracker.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.covidtracker.db.DataRoomDbase;
import com.example.covidtracker.db.MyDao;
import com.example.covidtracker.model.MyDataList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PostViewModel extends AndroidViewModel {

    private MyDao postDao;
    private ExecutorService executorService;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postDao = DataRoomDbase.Companion.getDatabase(application).dataDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<MyDataList>> getAllPosts() {
        return postDao.getMyData();
    }

    void savePost(MyDataList post) {
        executorService.execute(() -> postDao.addData(post));
    }

    void deletePost(MyDataList post) {
        executorService.execute(() -> postDao.delete(post));
    }
}
