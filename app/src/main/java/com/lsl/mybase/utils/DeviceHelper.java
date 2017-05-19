package com.lsl.mybase.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.lsl.mybase.base.MyApplication;


/**
 * @author scofieldandroid@gmail.com
 * @ClassName: DeviceHelper
 * @Description: 设备助手类
 * @date 2012-11-16 上午11:29:11
 * <p>
 * 方案说明： Wifi Mac地址+ Device ID (IMEI/MEID) + Android ID
 */
public class DeviceHelper {

    private static String TAG = DeviceHelper.class.getSimpleName();

    /**
     * 获得设备的唯一标示符
     *
     * @return
     */
    public static String getDeviceUniqueId() {
        String ret = StringUtils.mergeMutableArgs("|",
                "NAME:" + getDeviceModel(),
//									"DEVICE_ID:" + getDeviceId(),
                "DEVICE_ID:",
                "MAC:" + getMacAddress(),
//									"ANDROID_ID:" + getAndroidId(),
//									"IMSI:" + getIMSI(),
//									"IMEI:" + getDeviceId()
                "ANDROID_ID:",
                "IMSI:",
                "IMEI:"
        );
        return ret;
    }

    /**
     * 获取设备绑定唯一标识符
     */
    public static String getDeviceBindUniqueId() {
        return "SYS_TYPE:1|" + getDeviceUniqueId();
    }

    /**
     * <1>获得设备的Wifi Mac地址(开启Wi-Fi热点时为null)
     *
     * @return
     */
    private static String getMacAddress() {
        WifiManager wifi = (WifiManager) MyApplication.context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();

        String ret = info.getMacAddress();

        if (ret == null) {
            ret = "";
        }
        //e8:99:c4:7a:5e:88
        return ret;
    }

    /**
     *
     * 获取手机token值  唯一设备码
     * @return
     * @Title: getDeviceId
     * @Description: GSM卡为IMEI, CDMA卡为MEID
     * @returnType String
     */

    public static String getDeviceId() {
        String imei_meid = null;
        try {
            TelephonyManager mTelephonyMgr = (TelephonyManager) MyApplication.context.getSystemService(Context.TELEPHONY_SERVICE);
            imei_meid = mTelephonyMgr.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            imei_meid = "";
        }
        return imei_meid;
    }


    /**
     * <3>获得Android ID
     *
     * @return
     */

    private static String getAndroidId() {
        String ANDROID_ID = Settings.System.getString(MyApplication.context.getContentResolver(), Settings.System.ANDROID_ID);
        return ANDROID_ID;
    }

    /**
     * 获取IMSI地址，如果在飞行模式的时候是无法获取的。
     *
     * @return
     */

    private static String getIMSI() {
        String imsi;
        try {
            TelephonyManager mTelephonyMgr = (TelephonyManager) MyApplication.context.getSystemService(Context.TELEPHONY_SERVICE);
            imsi = mTelephonyMgr.getSubscriberId();
        } catch (Exception e) {
            e.printStackTrace();
            imsi = "";
        }
        return imsi;
    }

    /**
     * 获取设备的型号
     */
    public static String getDeviceModel() {
        String deviceModel = Build.MODEL;
        return deviceModel;
    }

    /**
     * 获取设备的厂商
     */
    public static String getDeviceBrand() {
        String deviceBrand = Build.BRAND;
        return deviceBrand;
    }

    /**
     * 获取app的安装时间
     */
    public static long getAppInstallTime(){
        try {
            PackageManager packageManager = MyApplication.context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(MyApplication.context.getPackageName(), 0);
            //应用装时间
            long firstInstallTime = packageInfo.firstInstallTime;
            //应用最后一次更新时间
            long lastUpdateTime = packageInfo.lastUpdateTime;
            return firstInstallTime;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
