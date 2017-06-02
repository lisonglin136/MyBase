package com.lsl.mybase.utils;

import android.app.Activity;
import android.content.Intent;

import com.lsl.mybase.R;


/**
 * 创建者： lsl
 * 创建时间： 2017/6/2 16:42
 * 描述：activity跳转工具类
 * 修改人：
 * 修改时间：
 * 备注：防止多次点击跳转，500ms类点击无效
 */
public class ActivityUtils {


    private  ActivityUtils(){

    }
    /**
     * 防止连续点击跳转两个页面
     */
    protected long lastClickTime;

    /**
     * 500ms内防止连续点击跳转两个页面
     *
     * @return
     */
    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 不带参数的intent意图跳转
     * @param activity 当前activity
     * @param clz   需要跳转的activity类
     */
    public void startActivity(Activity activity, Class clz){
        if (isFastDoubleClick()) {
            return;
        }
        Intent intent = new Intent(activity, clz);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    /**
     * 带参数的intent意图跳转
     * @param activity 当前activity
     * @param clz 需要跳转的activity类
     * @param intent 携带参数的intent意图
     */
    public void startActivity(Activity activity, Intent intent) {
        if (isFastDoubleClick()) {
            return;
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }


    /**
     * 不带参数的intent意图跳转，跳转后当前页面销毁
     * @param activity 当前activity
     * @param clz   需要跳转的activity类
     */
    public void startAndFinshActivity(Activity activity, Class clz){
        if (isFastDoubleClick()) {
            return;
        }
        Intent intent = new Intent(activity, clz);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        activity.finish();
    }

    /**
     * 带参数的intent意图跳转，跳转后当前页面销毁
     * @param activity 当前activity
     * @param clz 需要跳转的activity类
     * @param intent 携带参数的intent意图
     */
    public void startAndFinshActivity(Activity activity, Intent intent) {
        if (isFastDoubleClick()) {
            return;
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        activity.finish();
    }
}
