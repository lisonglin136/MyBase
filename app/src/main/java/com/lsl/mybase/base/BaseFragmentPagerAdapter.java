package com.lsl.mybase.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 创建者： lsl
 * 创建时间： 2017/6/2 15:34
 * 描述：viewpager中存放fragment时的adapter
 * 修改人：
 * 修改时间：
 * 备注：使用时直接使用即可不需要重写
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }
}
