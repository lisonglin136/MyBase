package com.lsl.mybase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * 作者: created by YH on 2017/4/13 16:19.
 * 描述: 日期时间工具类（主要在 交易查询-统计 板块使用）
 */

public class DateUtils {

    private static String mYear;    // 当前年
    private static String mMonth;   // 月
    private static String mDay;     // 日
    private static String mWay;     // 周几

    /**
     * 获取当前年月日
     */
    public static String getCurrentYearDayString() {

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        switchMonth(mMonth);
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        switchDay(mDay);
        return mYear + "-" + mMonth + "-" + mDay;
    }

    /**
     * 获取当前年月
     */
    public static String getCurrentYearMonthString() {

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        switchMonth(mMonth);
        return mYear + "-" + mMonth;
    }

    /**
     * 获取本周的开始时间(Calendar)
     */
    public static Calendar getCurrentTimeOfWeekStartCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.clear(Calendar.MINUTE);
        c.clear(Calendar.SECOND);
        c.clear(Calendar.MILLISECOND);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());

        // 需要提前一天（由周日变成周一）
        int day = c.get(Calendar.DAY_OF_MONTH) + 1;
        c.set(Calendar.DAY_OF_MONTH, day);

        return c;
    }

    /**
     * 获取本周的开始时间(String)
     */
    public static String getCurrentTimeOfWeekStartString() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.clear(Calendar.MINUTE);
        c.clear(Calendar.SECOND);
        c.clear(Calendar.MILLISECOND);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());

        // 需要提前一天（由周日变成周一）
        int day = c.get(Calendar.DAY_OF_MONTH) + 1;
        c.set(Calendar.DAY_OF_MONTH, day);

        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        switchMonth(mMonth);
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        switchDay(mDay);
        return mYear + "-" + mMonth + "-" + mDay;
    }

    /**
     * 获取Day阶段日期,(多少天之前，或者多少天之后)，返回为String
     *
     * @param howDay int day = 1  一天以后
     */
    public static String getDateThrowDayString(int howDay) {

        Calendar c = Calendar.getInstance();                    // 当时的日期和时间
        int day;    // 需要更改的天数

        day = c.get(Calendar.DAY_OF_MONTH) + howDay;
        c.set(Calendar.DAY_OF_MONTH, day);

        mYear = String.valueOf(c.get(Calendar.YEAR));           // 获取年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);     // 获取月份
        switchMonth(mMonth);
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));    // 获取月份的日期号码
        switchDay(mDay);
        return mYear + "-" + mMonth + "-" + mDay;
    }

    /**
     * 获取Day阶段日期,(多少天之前，或者多少天之后)，返回为Calendar日期格式
     *
     * @param howDay int day = 1  一天以后
     */
    public static Calendar getDateThrowDayCalendar(int howDay) {

        Calendar c = Calendar.getInstance();                    // 当时的日期和时间
        int day;    // 需要更改的天数

        day = c.get(Calendar.DAY_OF_MONTH) + howDay;
        c.set(Calendar.DAY_OF_MONTH, day);

        return c;
    }

    /**
     * 获取Week阶段日期,(多少星期之前，或者多少星期之后)，返回为String
     *
     * @param howDay int day = 7  七天以后
     */
    public static String getDateThrowWeekString(int howDay) {

        Calendar c = getCurrentTimeOfWeekStartCalendar();       // 本周的开始时间
        int day;    // 需要更改的天数

        day = c.get(Calendar.DAY_OF_MONTH) + howDay;
        c.set(Calendar.DAY_OF_MONTH, day);

        mYear = String.valueOf(c.get(Calendar.YEAR));           // 获取年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);     // 获取月份
        switchMonth(mMonth);
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));    // 获取月份的日期号码
        switchDay(mDay);
        return mYear + "-" + mMonth + "-" + mDay;
    }

    /**
     * 获取Month阶段日期,(多少月之前，或者多少月之后)，返回为String
     *
     * @param howMonth int month = 1  一月以后
     */
    public static String getDateThrowMonthString(int howMonth) {

        Calendar c = getCurrentMonthFristDay();                  // 当时的日期和时间
        int month;    // 需要更改的月数

        month = c.get(Calendar.MONTH) + howMonth;
        c.set(Calendar.MONTH, month);

        mYear = String.valueOf(c.get(Calendar.YEAR));           // 获取年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);     // 获取月份
        switchMonth(mMonth);
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));    // 获取月份的日期号码
        switchDay(mDay);
        return mYear + "-" + mMonth;
    }

    /**
     * 获取Month阶段日期,(多少月之前，或者多少月之后)，返回为Calendar
     *
     * @param howMonth int month = 1  一月以后
     */
    public static Calendar getDateThrowMonthCalendar(int howMonth) {

        Calendar c = getCurrentMonthFristDay();                  // 当时的日期和时间
        int month;    // 需要更改的月数

        month = c.get(Calendar.MONTH) + howMonth;
        c.set(Calendar.MONTH, month);

        return c;
    }


    /**
     * 获取Month阶段日期,(当前月份的第一天)，返回为Calendar
     *
     * @param howMonth int month = 1  一月以后
     */
    public static Calendar getMonthFirstCalendar(int howMonth) {

        Calendar c = getCurrentMonthFristDay();                  // 当前月的第一天
        int month;    // 需要更改的月数

        month = c.get(Calendar.MONTH) + howMonth;
        c.set(Calendar.MONTH, month);

        //获取修改后的月份的第一天
        Calendar monthFristDay = getMonthFristDay(c);

        LogUtil.w(switchCalendarDate(monthFristDay));

        return monthFristDay;
    }


    /**
     * 获取Month阶段日期,(当前月份的最后一天)，返回为Calendar
     *
     * @param howMonth int month = 1  一月以后
     */
    public static Calendar getMonthLastCalendar(int howMonth) {

        Calendar c = getCurrentMonthFristDay();                  // 当前月的第一天
        int month;    // 需要更改的月数

        month = c.get(Calendar.MONTH) + howMonth;
        c.set(Calendar.MONTH, month);

        //获取修改后的月份的最后一天
        Calendar monthLastDay = getMonthLastDay(c);

        LogUtil.w(switchCalendarDate(monthLastDay));

        return monthLastDay;
    }

    //设置成当前年月的第一天
    public static Calendar getCurrentMonthFristDay() {

        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c.set(Calendar.DAY_OF_MONTH, 1);

        return c;
    }

    //获取修改后的月份的第一天
    public static Calendar getMonthFristDay(Calendar c) {
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c;
    }

    //获取修改后的月份的最后一天
    public static Calendar getMonthLastDay(Calendar c) {

        Calendar monthFristDay = getMonthFristDay(c);
        monthFristDay.add(Calendar.MONTH, 1);
        monthFristDay.add(Calendar.DATE, -1);

        return monthFristDay;
    }


    //转换传入的日期为年月日
    public static String switchCalendarDate(Calendar c) {
        mYear = String.valueOf(c.get(Calendar.YEAR));           // 获取年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);     // 获取月份
        switchMonth(mMonth);
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));    // 获取月份的日期号码
        switchDay(mDay);
        return mYear + "-" + mMonth + "-" + mDay;
    }

    //天数的显示切换
    private static void switchDay(String day) {
        if (Integer.parseInt(day) < 10) {
            mDay = "0" + day;
        }
    }

    //月份的显示切换
    private static void switchMonth(String month) {
        if (Integer.parseInt(month) < 10) {
            mMonth = "0" + month;
        }
    }

    //与当前时间比较是早还是晚
    public static boolean isMoreThanCurrentDate(Calendar inDate) {

        //currentDate 13比inDate 14早,返回-1,false
        //currentDate 13与inDate 13相同,返回0,true
        //currentDate 13比inDate 12晚,返回1,true

        Calendar currentDate = Calendar.getInstance();

        int i = currentDate.compareTo(inDate);
        if (i == -1) {
            return true;
        }

        return false;

    }


    /******************************************************* 下方不使用的方法  *******************************************************/
    /******************************************************* 下方不使用的方法  *******************************************************/

    /**
     * 获取阶段日期
     *
     * @param dateType char datetype = '7'
     */
    public static String getStringDate(char dateType) {
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int hour;   // 需要更改的小时
        int day;    // 需要更改的天数
        switch (dateType) {
            case '0': // 1小时前
                hour = c.get(Calendar.HOUR_OF_DAY) - 1;
                c.set(Calendar.HOUR_OF_DAY, hour);
                // System.out.println(df.format(c.getTime()));
                break;
            case '1': // 2小时前
                hour = c.get(Calendar.HOUR_OF_DAY) - 2;
                c.set(Calendar.HOUR_OF_DAY, hour);
                // System.out.println(df.format(c.getTime()));
                break;
            case '2': // 3小时前
                hour = c.get(Calendar.HOUR_OF_DAY) - 3;
                c.set(Calendar.HOUR_OF_DAY, hour);
                // System.out.println(df.format(c.getTime()));
                break;
            case '3': // 6小时前
                hour = c.get(Calendar.HOUR_OF_DAY) - 6;
                c.set(Calendar.HOUR_OF_DAY, hour);
                // System.out.println(df.format(c.getTime()));
                break;
            case '4': // 12小时前
                hour = c.get(Calendar.HOUR_OF_DAY) - 12;
                c.set(Calendar.HOUR_OF_DAY, hour);
                // System.out.println(df.format(c.getTime()));
                break;
            case '5': // 一天前
                day = c.get(Calendar.DAY_OF_MONTH) - 1;
                c.set(Calendar.DAY_OF_MONTH, day);
                // System.out.println(df.format(c.getTime()));
                break;
            case '6': // 一星期前
                day = c.get(Calendar.DAY_OF_MONTH) - 7;
                c.set(Calendar.DAY_OF_MONTH, day);
                // System.out.println(df.format(c.getTime()));
                break;
            case '7': // 一个月前
                day = c.get(Calendar.DAY_OF_MONTH) - 30;
                c.set(Calendar.DAY_OF_MONTH, day);
                // System.out.println(df.format(c.getTime()));
                break;
        }

        mYear = String.valueOf(c.get(Calendar.YEAR));           // 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);     // 获取当前月份
        switchMonth(mMonth);
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));    // 获取当前月份的日期号码
        switchDay(mDay);
        return mYear + "-" + mMonth + "-" + mDay;
    }

    /**
     * 获取当前是周几
     */
    public static String getWeekString() {
        final Calendar c = Calendar.getInstance();
        mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "周天";
        } else if ("2".equals(mWay)) {
            mWay = "周一";
        } else if ("3".equals(mWay)) {
            mWay = "周二";
        } else if ("4".equals(mWay)) {
            mWay = "周三";
        } else if ("5".equals(mWay)) {
            mWay = "周四";
        } else if ("6".equals(mWay)) {
            mWay = "周五";
        } else if ("7".equals(mWay)) {
            mWay = "周六";
        }
        return mDay;
    }

    /**
     * 根据当前日期获得是星期几
     */
    public static String getWeek(String time) {
        String Week = "";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(time));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        if ((c.get(Calendar.DAY_OF_WEEK)) == 1) {
            Week += "周天";
        }
        if ((c.get(Calendar.DAY_OF_WEEK)) == 2) {
            Week += "周一";
        }
        if ((c.get(Calendar.DAY_OF_WEEK)) == 3) {
            Week += "周二";
        }
        if ((c.get(Calendar.DAY_OF_WEEK)) == 4) {
            Week += "周三";
        }
        if ((c.get(Calendar.DAY_OF_WEEK)) == 5) {
            Week += "周四";
        }
        if ((c.get(Calendar.DAY_OF_WEEK)) == 6) {
            Week += "周五";
        }
        if ((c.get(Calendar.DAY_OF_WEEK)) == 7) {
            Week += "周六";
        }
        return Week;
    }

    /**
     * 获取今天往后一周的日期（年-月-日）
     */
    public static List<String> get7date() {

        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        String date = sim.format(c.getTime());
        dates.add(date);
        for (int i = 0; i < 6; i++) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            date = sim.format(c.getTime());
            dates.add(date);

        }
        return dates;

    }

    /**
     * 获取今天往后一周的日期（几月几号）
     */
    public static List<String> getSevendate() {
        List<String> dates = new ArrayList<String>();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        for (int i = 0; i < 7; i++) {
            mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            String date = mMonth + "月" + mDay + "日";
            dates.add(date);
        }
        return dates;
    }

    /**
     * 获取今天往后一周的集合
     */
    public static List<String> get7week() {
        String week = "";
        List<String> weeksList = new ArrayList<String>();
        List<String> dateList = get7date();
        for (String s : dateList) {
            if (s.equals(getCurrentYearDayString())) {
                week = "今天";
            } else {
                week = getWeek(s);
            }
            weeksList.add(week);
        }
        return weeksList;
    }


}


