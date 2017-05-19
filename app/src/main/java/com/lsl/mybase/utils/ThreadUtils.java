package com.lsl.mybase.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @创建 YH
 * @描述 主线程、子线程管理
 */

public class ThreadUtils {
    //初始化单线程的线程池
    private static Executor sExecutor = Executors.newSingleThreadExecutor();

    //使用带参数的构造函数（looper）,即是在主线程
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    //子线程
    public static void runOnStbThread(Runnable runnable) {
        sExecutor.execute(runnable);

    }

    //主线程
    public static void runOnUiThread(Runnable runnable) {
        sHandler.post(runnable);

    }
}
