package com.jara.autotestdemo;

import android.app.Application;

/**
 * Created by Administrator on 2017-12-8.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
