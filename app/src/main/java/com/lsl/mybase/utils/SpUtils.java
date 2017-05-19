package com.lsl.mybase.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @创建 YH
 * @描述 sp的工具类
 */

public class SpUtils {
    private static final String SP_NAME = "config";//文件名
    private static SharedPreferences sp;

    /**
     * 保存String的方法
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }
        Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }


    /**
     * 取出的String
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }
        String result = sp.getString(key, null);
        return result;
    }

    /**
     * 存入的boolean
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }
        Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 取出的boolean
     *
     * @param context
     * @param key
     */
    public static Boolean getBoolean(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }
        boolean result = sp.getBoolean(key, false);
        return result;
    }

    /**
     * 存入的int
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }
        Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    /**
     * 取出的int
     *
     * @param context
     * @param key
     */
    public static int getInt(Context context, String key) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        }
        int result = sp.getInt(key, -1);
        return result;
    }

}
