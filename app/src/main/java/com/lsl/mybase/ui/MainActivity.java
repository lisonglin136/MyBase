package com.lsl.mybase.ui;

import android.content.Intent;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.lsl.mybase.R;
import com.lsl.mybase.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {


    @BindView(R.id.bt)
    Button mBt;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }


    @OnClick(R.id.bt)
    public void onViewClicked() {

        //1秒内连续点击此按钮无效
        RxView.clicks(mBt)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(MainActivity.this, SampleActivity.class));
                    }
                });
    }


}
