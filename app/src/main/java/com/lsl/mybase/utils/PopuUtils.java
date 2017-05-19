package com.lsl.mybase.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * author:Created by lsl on 2017/4/24 10:01.
 * description:popupwindow弹窗工具类
 */

public class PopuUtils {

    private PopuUtils() {
    }


    /**
     * 调用时间选择器
     * @param context Context
     * @param tv 设置时间的TextView
     */
    public static void getTimeFromSelect(Context context, final TextView tv) {

        //时间选择器
        TimePickerView mPvTime = new TimePickerView.Builder(context, new TimePickerView
                .OnTimeSelectListener() {
            //选中事件回调
            @Override
            public void onTimeSelect(Date date, View v) {
                String time = getTime(date);
                tv.setText(time);
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                .build();

        //注：根据需求来决定是否使用该方法（一般是精确到秒的情况）
        //此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        mPvTime.setDate(Calendar.getInstance());
        mPvTime.show();

    }


    //界面显示
    private static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


}
