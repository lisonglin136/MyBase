package com.lsl.mybase.utils;


import com.lsl.mybase.base.MyApplication;

/**
 * 作者: created by YH on 2017/3/23 11:30.
 * 描述: 数值获取工具类
 */
public class ValueUtil {

    /**
     * 根据String的资源id,获得字符串
     */
    public static String getString(int resId) {
        return MyApplication.context.getString(resId);
    }

}
