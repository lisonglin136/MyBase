package com.lsl.mybase.utils;

import android.util.Log;

/**
 * 作者: created by YH on 2017/3/20 17:15.
 * 描述: Log的工具类
 */

public class LogUtil {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数

    private LogUtil() {
        /* Protect from instantiations */

    }

    /**
     * 设置返回值，根据返回值决定是否显示log
     * @return
     */
    public static boolean isDebuggable() {
        //return BuildConfig.DEBUG;
        return true;
    }

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }


    public static void e(String message) {
        if (!isDebuggable())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e("TAG"+"["+className+"]", createLog(message));
    }


    public static void i(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i("TAG"+"["+className+"]", createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d("TAG"+"["+className+"]", createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v("TAG"+"["+className+"]", createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w("TAG"+"["+className+"]", createLog(message));
    }

    public static void wtf(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf("TAG"+"["+className+"]", createLog(message));
    }

}
