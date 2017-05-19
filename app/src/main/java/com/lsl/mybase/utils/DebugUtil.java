package com.lsl.mybase.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;

/**
 * author:Created by lsl on 2017/5/17 14:35.
 * description:控制台日志打印工具类
 */

public class DebugUtil {
    public static final String TAG = "com.guoyie.www.gyapp";

    //在代码中要打印log,就直接DebugUtil.debug(....).然后如果发布的时候,就直接把这个类的DEBUG 改成false,这样所有的log就不会再打印在控制台.
    public static final boolean DEBUG = true;

    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void debug(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void log(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void log(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, "***************" + msg);
        }
    }


    public static void error(String tag, String error) {

        if (DEBUG) {

            Log.e(tag, error);
        }
    }

    public static void error(String error) {

        if (DEBUG) {

            Log.e(TAG, error);
        }
    }

    public static void toJson(String json) {
        if (DEBUG) {
            LogUtils.json(json);
        }
    }
}
