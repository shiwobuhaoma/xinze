package com.xinze.xinze.module.send.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author lxf
 * Created by Administrator on 2017/9/17.
 */

public class SelectPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;

    public SelectPageAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
}
