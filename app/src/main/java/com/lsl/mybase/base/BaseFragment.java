package com.lsl.mybase.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lsl.mybase.R;
import com.lsl.mybase.rx.RxManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {


    protected View rootView;
    public RxManager mRxManager;
    protected Context mContext;
    protected InputMethodManager manager;

    Unbinder unbinder;
    private FrameLayout container;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.activity_base, container, false);

        initBaseViews(inflater,rootView);


        unbinder = ButterKnife.bind(this, rootView);

        mContext = getActivity();
        manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mRxManager = new RxManager();
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    //标题设置
    protected abstract void initTitle();

    //获取布局文件
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initView();


    //标题初始化
    private void initBaseViews(LayoutInflater inflater, View rootView) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);//标题栏

        mTitleContainer = (LinearLayout) rootView.findViewById(R.id.title_ll);//标题容器
        mTitleTextView = (TextView) rootView.findViewById(R.id.title_tv);//标题
        mTitleRightArrowIv = (ImageView) rootView.findViewById(R.id.title_right_arrow_iv);//标题中间文字右边箭头

        mContentLayout = (FrameLayout) rootView.findViewById(R.id.layout_content);//主体

        mLeftBackWardLinearLayout = (LinearLayout) rootView.findViewById(R.id.back);//左边返回按钮线性布局
        mLeftBackWardText = (TextView) rootView.findViewById(R.id.left_text_tv);//左边返回按钮文字

        mRightContainer = (LinearLayout) rootView.findViewById(R.id.right_ll);//标题右边容器
        mRightImage = (ImageView) rootView.findViewById(R.id.right_image); //标题右边图片
        mRightText = (TextView) rootView.findViewById(R.id.right_text); //标题右边文字
        container = (FrameLayout) rootView.findViewById(R.id.layout_content);
        initTitle();
        container.removeAllViews();
        container.addView(inflater.inflate(getLayoutId(), container, false));
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
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            // 隐藏软键盘
            imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken()
                    , 0);
            getActivity().finish();
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


    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 隐藏软键盘
     */
    protected void hideKeyboard(EditText editText) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     */
    protected void showKeyboard(EditText editText) {
        if (getActivity().getWindow().getAttributes().softInputMode != WindowManager.LayoutParams
                .SOFT_INPUT_STATE_HIDDEN) {
            if (getActivity().getCurrentFocus() != null)
                manager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRxManager.clear();
        unbinder.unbind();
    }


}
