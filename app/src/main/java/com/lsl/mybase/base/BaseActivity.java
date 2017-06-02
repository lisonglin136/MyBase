package com.lsl.mybase.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lsl.mybase.R;
import com.lsl.mybase.global.AppManager;
import com.lsl.mybase.model.ResponseModel;
import com.lsl.mybase.network.RetrofitUtil;
import com.lsl.mybase.pullToRefresh.PullToRefreshBase;
import com.lsl.mybase.pullToRefresh.PullToRefreshListView;
import com.lsl.mybase.rx.RxManager;
import com.lsl.mybase.utils.LightStatusBarUtils;
import com.lsl.mybase.utils.LogUtil;
import com.lsl.mybase.utils.StatusBarUtils;
import com.lsl.mybase.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;


/**
 * 创建者： lsl
 * 创建时间： 2017/6/2 15:34
 * 描述：所有activity的基类，必须继承
 * 修改人：
 * 修改时间：
 * 备注：
 */

public abstract class BaseActivity extends AppCompatActivity {

    public final static int READ_EXTERNAL_STORAGE = 0x01;
    public final static int CAMERA = 0x02;
    public final static int CALL_PHONE = 0x03;

    public Context mContext;
    public RxManager mRxManager;
    private Unbinder bind;

    private LinearLayout mTitleContainer;//标题容器
    private TextView mTitleTextView;//标题
    private ImageView mTitleRightArrowIv;//标题文字右边箭头

    private FrameLayout mContentLayout;//主体

    private LinearLayout mLeftBackWardLinearLayout;//左边返回按钮线性布局
    private TextView mLeftBackWardText;//左边返回按钮文字

    private LinearLayout mRightContainer;//标题右边容器
    private ImageView mRightImage; //标题右边图片
    private TextView mRightText; //标题右边图片
    private Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetcontentView();
        initBaseViews();

        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        mContext = this;
        initStatusBar(false);
        initView();
    }


    /*************************************************************/
    /**
     * 子类实现的方法，布局传入
     */
    protected abstract int getLayoutId();

    /**
     * 子类实现的方法，标题栏设置
     */
    protected abstract void initTitle();

    /**
     * 子类实现的方法，布局设置
     */
    protected abstract void initView();

    /*************************************************************/

    //标题初始化
    private void initBaseViews() {
        super.setContentView(R.layout.activity_base);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);//标题栏

        mTitleContainer = (LinearLayout) findViewById(R.id.title_ll);//标题容器
        mTitleTextView = (TextView) findViewById(R.id.title_tv);//标题
        mTitleRightArrowIv = (ImageView) findViewById(R.id.title_right_arrow_iv);//标题中间文字右边箭头

        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);//主体

        mLeftBackWardLinearLayout = (LinearLayout) findViewById(R.id.back);//左边返回按钮线性布局
        mLeftBackWardText = (TextView) findViewById(R.id.left_text_tv);//左边返回按钮文字

        mRightContainer = (LinearLayout) findViewById(R.id.right_ll);//标题右边容器
        mRightImage = (ImageView) findViewById(R.id.right_image); //标题右边图片
        mRightText = (TextView) findViewById(R.id.right_text); //标题右边文字
        initTitle();
        initBaseClick();

    }


    /*********************标题初始化***************************/

    public void hasNoTitle() {
        initTitle(false, false, false, "", "", false, 0, "", 0);
    }


    /**
     * 标题栏仅有中间文字和中间下箭头时设置
     *
     * @param titleString   标题中间文字
     * @param showDownArrow 标题中间文字下方下箭头
     */
    public void initTitle(String titleString, boolean showDownArrow) {
        initTitle(true, false, false, "", titleString, showDownArrow, 0, "", 0);
    }

    /**
     * 标题含有||左边文字||中间文字
     *
     * @param titleString   标题中间文字
     * @param leftString    标题左边文字
     * @param showDownArrow 标题中间文字下方下箭头
     */
    public void initTitle(String titleString, String leftString, boolean showDownArrow) {
        initTitle(true, true, false, leftString, titleString, showDownArrow, 0, "", 0);
    }

    /**
     * 标题含有||左边文字||中间文字||右边文字
     *
     * @param titleString   标题中间文字
     * @param leftString    标题左边文字
     * @param rightString   标题右边文字
     * @param showDownArrow 标题中间文字下方下箭头
     */
    public void initTitle(String titleString, String leftString, String rightString, boolean
            showDownArrow) {
        initTitle(true, true, true, leftString, titleString, showDownArrow, 1, rightString, 0);
    }

    /**
     * 标题含有||左边文字||中间文字||右边图片
     *
     * @param titleString   标题中间文字
     * @param leftString    标题左边文字
     * @param imageResId    标题右边图片ID
     * @param showDownArrow 标题中间文字下方下箭头
     */
    public void initTitle(String titleString, String leftString, int imageResId, boolean
            showDownArrow) {
        initTitle(true, true, true, leftString, titleString, showDownArrow, 0, "", imageResId);
    }

    /**
     * 初始化标题最终方法
     *
     * @param showTitle     是否显示标题栏
     * @param showLeft      是否显示标题左边
     * @param showRight     是否显示标题右边
     * @param leftString    标题左边文字
     * @param titleString   标题文字
     * @param showDownArrow 是否显示标题中间文字右边箭头
     * @param type          显示类型 0为图片/1为文字
     * @param rightString   标题右边文字
     * @param imageResId    标题右边图片id
     */
    protected void initTitle(boolean showTitle, boolean showLeft, boolean showRight, String
            leftString, String titleString, boolean showDownArrow, int type, String rightString, int
                                     imageResId) {
        if (mToolbar != null) {
            if (showTitle) {
                mTitleTextView.setText(titleString);
                if (showDownArrow) {
                    mTitleRightArrowIv.setVisibility(View.VISIBLE);
                } else {
                    mTitleRightArrowIv.setVisibility(View.GONE);
                }
                if (mLeftBackWardLinearLayout != null && mLeftBackWardText != null) {
                    if (showLeft) {
                        mLeftBackWardText.setText(leftString);
                        mLeftBackWardLinearLayout.setVisibility(View.VISIBLE);
                    } else {
                        mLeftBackWardLinearLayout.setVisibility(View.GONE);
                    }
                }

                if (mRightContainer != null && mRightImage != null && mRightText != null) {
                    if (showRight) {
                        switch (type) {
                            case 0:
                                //暂时用下优化下
                                Glide.with(this).load(imageResId).into(mRightImage);
                                mRightText.setVisibility(View.GONE);
                                break;
                            case 1:
                                mRightText.setVisibility(View.VISIBLE);
                                mRightText.setText(rightString);
                                mRightImage.setVisibility(View.GONE);
                                break;
                        }
                        mRightContainer.setVisibility(View.VISIBLE);
                    } else {
                        mRightContainer.setVisibility(View.GONE);
                    }
                }

            } else {
                mToolbar.setVisibility(View.GONE);
            }
        }
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }

    /*********************标题初始化***************************/
    /**
     * 统一处理点击事件
     */
    private void initBaseClick() {
        //标题点击事件
        mTitleContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleClick();
            }
        });
        //左边按钮的点击事件
        mLeftBackWardLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftBackward(v);
            }
        });
        //右边按钮的点击事件
        mRightContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightForward(v);
            }
        });
    }

    /**
     * 返回按钮点击后触发，复写可改逻辑
     *
     * @param backwardView
     */
    protected void onLeftBackward(View backwardView) {
        if (backwardView.getVisibility() == View.VISIBLE) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            // 隐藏软键盘
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            finish();
        }// else ignored
    }


    /**
     * 标题的点击事件，子类去完成，非强制，不抽象
     */
    protected void onTitleClick() {
    }

    /**
     * 右边按钮点击后触发，子类完成，非强制，不抽象
     *
     * @param forwardView
     */
    protected void onRightForward(View forwardView) {

    }

    /*************************************************************/
    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }


    /**
     * 状态栏修改
     *
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

    /**
     * 设置layout前配置
     */
    protected void doBeforeSetcontentView() {
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
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



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
        bind.unbind();
        AppManager.getAppManager().finishActivity(this);
    }


    public <T> void post(String url, T requestBody, Observer<ResponseModel> observer) {
        Subscription subscription = RetrofitUtil.postNetData(url,
                requestBody, observer);
        //activity销毁的时候调用防止内存泄漏
        mRxManager.add(subscription);
    }

    /**
     * 初始化PullToRefreshListView基本设置
     *
     * @param pullToRefreshListView
     */
    public void initPullToRefreshListView(final PullToRefreshListView pullToRefreshListView) {
        //设置当前上拉加载更多是否可用
        pullToRefreshListView.setPullLoadEnabled(true);
        //设置当前下拉刷新是否可用
        pullToRefreshListView.setPullRefreshEnabled(true);
        //滑动到底部是否自动加载更多数据
        pullToRefreshListView.setScrollLoadEnabled(false);
        //下拉刷新回调事件
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase
                .OnRefreshListener<ListView>() {

            //下拉
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                ToastUtils.showToast(BaseActivity.this, "已经没有更多下拉数据了");
                pullToRefreshListView.onPullDownRefreshComplete();
            }

            //上拉
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ToastUtils.showToast(BaseActivity.this, "已经没有更多上拉数据了");
                pullToRefreshListView.onPullUpRefreshComplete();
            }
        });
    }
}
