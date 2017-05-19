package com.lsl.mybase.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build;

import com.lsl.mybase.base.MyApplication;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * 跟手机有关的工具类
 *
 * @author carlos carlosk@163.com
 * @version 创建时间：2013-3-10 上午11:30:38 类说明
 */

public class PhoneUtil {


    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取版本号
     *
     * @return
     * @author carlos carlosk@163.com
     * @version 创建时间：2013-3-17 下午2:56:17
     */
    public static String getVersionCode() {

        String versionCode = "";
        try {

            versionCode = "" + MyApplication.context.getPackageManager().getPackageInfo
                    (MyApplication.context.getPackageName(), 0).versionCode;

        } catch (NameNotFoundException e) {

            e.printStackTrace();

        }

        return versionCode;

    }

    /**
     * 获取版本名称
     *
     * @return
     * @author carlos carlosk@163.com
     * @version 创建时间：2013-3-17 下午2:58:57
     */
    public static String getVersionName() {
        String versionName = "";
        try {

            PackageManager manager = MyApplication.context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(MyApplication.context.getPackageName(), 0);
            versionName = info.versionName;

        } catch (NameNotFoundException e) {

            e.printStackTrace();

        }

        return versionName;
    }

    /**
     * 获取配置文件信息
     *
     * @return
     * @author allen
     * @version 2013-8-27 上午9:25:50
     */
    public static String getSettingInfo() {

        return getVersionCode() + getVersionName();

    }

    /**
     * 获取签值信息
     *
     * @return
     * @author allen
     * @version 2013-8-27 下午4:15:04
     */
    public static byte[] getSingInfo() {

        try {
            PackageManager manager = MyApplication.context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(MyApplication.context
					.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            return signs[0].toByteArray();
//			return parseSignature(sign.toByteArray()); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取应用安装时间
     *
     * @return
     */
    public static int getInstallTime() {
        try {
            PackageManager packageManager = MyApplication.context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(MyApplication.context
					.getPackageName(), 0);
            //应用装时间
            long firstInstallTime = packageInfo.firstInstallTime;
            //应用最后一次更新时间
            long lastUpdateTime = packageInfo.lastUpdateTime;
            return (int) firstInstallTime;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取本地ip
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return null;
    }

}
