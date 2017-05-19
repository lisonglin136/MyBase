package com.lsl.mybase.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @创建 YH
 * @描述 吐司工具类
 */

public class ToastUtils {
    private static Toast sToast;

    public static void showToast(Context context, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.show();
    }
}
