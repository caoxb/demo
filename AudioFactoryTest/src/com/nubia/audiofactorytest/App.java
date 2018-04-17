package com.nubia.audiofactorytest;

import android.app.Application;

import com.nubia.audiofactorytest.utils.CrashHandler;

/**
 * Created by ZHONGYUAN on 2017/11/5.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new CrashHandler(this).init();
    }
}
