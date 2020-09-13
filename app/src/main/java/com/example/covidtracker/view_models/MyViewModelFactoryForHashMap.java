package com.example.covidtracker.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

public class MyViewModelFactoryForHashMap implements ViewModelProvider.Factory {
    private String param;


    public MyViewModelFactoryForHashMap(String param) {
        this.param = param;
    }


    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        return (T) new MainViewModel();
    }
}