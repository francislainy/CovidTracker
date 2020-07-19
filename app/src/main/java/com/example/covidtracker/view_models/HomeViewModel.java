package com.example.covidtracker.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> userLiveData;
    private ArrayList<String> userArrayList;

    public HomeViewModel() {
        userLiveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<ArrayList<String>> getUserMutableLiveData() {
        return userLiveData;
    }

    private void init() {
        populateList();
        userLiveData.setValue(userArrayList);
    }

    private void populateList() {

        userArrayList = new ArrayList<>();
        userArrayList.add("Contact Tracing");
        userArrayList.add("COVID Check-In");
        userArrayList.add("Terms & Conditions");
        userArrayList.add("App Metrics");
        userArrayList.add("Leave");

    }
}

//    val list = arrayOf(
//            "Contact Tracing",
//            "COVID Check-In",
//            "Data Protection Information Notice",
//            "Terms & Conditions",
//            "App Metrics",
//            "Leave"
//    )