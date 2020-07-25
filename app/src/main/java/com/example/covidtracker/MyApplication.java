package com.example.covidtracker;

import android.app.Application;


/**
 * As per https://stackoverflow.com/a/33923946/6654475
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/Lato-Regular.ttf");
        TypefaceUtil.overrideFont(getApplicationContext(), "LATO", "fonts/Lato-Regular.ttf");
    }
}
