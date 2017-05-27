package com.lsl.mybase.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lsl.mybase.R;
import com.lsl.mybase.global.AppManager;
import com.lsl.mybase.rx.RxManager;
import com.lsl.mybase.utils.LightStatusBarUtils;
import com.lsl.mybase.utils.LogUtil;
import com.lsl.mybase.utils.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by boxu on 2017/4/1.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public final static int READ_EXTERNAL_STORAGE = 0x01;
    public final static int CAMERA = 0x02;
    public final static int CALL_PHONE = 0x03;

    public Context mContext;
    public RxManager mRxManager;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        bind= ButterKnife.bind(this);
        mContext = this;
        initStatusBar(false);
        initView();
    }

    /**
     * 状态栏修改
     * @param isStatus
     */
    protected void initStatusBar(boolean isStatus) {
        LightStatusBarUtils.setLightStatusBar(this, isStatus);
    }

    protected void initStatusBarColor(boolean isStatus) {
        LogUtil.i("initStatusBarColor::" + isStatus);
        if (isStatus) {
            StatusBarUtils.setColor(this, ContextCompat.getColor(mContext, R.color.lucency));
        }
    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    public abstract void initView();

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 针对6.0动态请求权限问题
     * 判断是否允许此权限
     *
     * @param permissions
     * @return
     */
    protected boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 动态请求权限
     *
     * @param code
     * @param permissions
     */
    protected void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readSdCard();
                } else {
                    showToast("读取内存卡权限已被拒绝");
                }
                break;
            case CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    showToast("拍照权限已被拒绝");
                }
                break;
            case CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                } else {
                    showToast("拨打电话权限已被拒绝");
                }
                break;
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

    }
    /**
     * 启动相机
     */
    protected void startCamera() {

    }

    /**
     * 读取内存卡信息
     */
    protected void readSdCard() {

    }

    /**
     * 拨打电话权限
     */
    protected void callPhone() {

    }

    /**
     * 防止连续点击跳转两个页面
     */
    protected long lastClickTime;

    /**
     * 500ms内防止连续点击跳转两个页面
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
     * @param clz
     * @return void 返回类型
     * @Title: startActivity
     * @Description: 进入到下个activity
     * @author luck
     */
    protected void go2Activity(Class clz) {
        if (isFastDoubleClick()) {
            return;
        }
        Intent intent = new Intent(mContext, clz);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        bind.unbind();
        AppManager.getAppManager().finishActivity(this);
    }
}
