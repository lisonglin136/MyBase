package com.lsl.mybase.utils;

import android.content.pm.PackageManager;

/**
 * 作者: created by YH on 2017/4/5 9:44.
 * 描述: 权限申请的权限类
 */

public abstract class PermissionUtil {

    public static boolean verifyPermissions(int[] grantResults) {
        if(grantResults.length < 1){
            return false;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}
