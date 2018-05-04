package com.xinze.xinze.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author lxf
 * Fragment 的基类
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity=(Activity)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(initLayout(), null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    /**
     * fragment 的布局文件
     * @return 布局文件的资源id
     */
    protected abstract int initLayout() ;
    /**
     * fragment 的布局文件中控件的初始化
     *
     */
    protected abstract void initView() ;
    /**
     * 从网络获取数据
     *
     */
    protected  void initData() {

    }
    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    protected void openActivity(Class clazz) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);
    }
    protected void openActivity(Class clazz,String key ,String value) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra(key,value);
        startActivity(intent);
    }
}
