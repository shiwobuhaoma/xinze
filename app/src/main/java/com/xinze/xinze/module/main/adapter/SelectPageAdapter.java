package com.xinze.xinze.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 首页的pageAdapter
 * @author lxf
 * Created by Administrator on 2017/9/17.
 */

public class SelectPageAdapter extends FragmentPagerAdapter {
    private final FragmentManager fm;
    private ArrayList<Fragment> list;

    public SelectPageAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.fm = fm;
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

    public void setList(ArrayList<Fragment> list){
        this.list.clear();
        this.list.addAll(list) ;
        notifyDataSetChanged();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container,position);
        fm.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
        Fragment fragment = list.get(position);
        fm.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }
}
