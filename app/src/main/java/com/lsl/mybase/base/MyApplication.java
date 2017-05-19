package com.lsl.mybase.base;

import android.app.Application;
import android.content.Context;

/**
 * author:Created by lsl on 2017/5/17 14:18.
 * description:application
 */

public class MyApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
