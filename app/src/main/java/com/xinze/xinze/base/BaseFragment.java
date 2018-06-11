package com.xinze.xinze.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xinze.xinze.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author lxf
 * Fragment 的基类
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected Activity mActivity;
    /**
     * Fragment的View加载完毕的标记
     */
    private boolean isViewCreated;

    /**
     * Fragment对用户可见的标记
     */
    private boolean isUIVisible;
    protected boolean pageEndFlag = false;
    protected View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(initLayout(), null);
        unbinder = ButterKnife.bind(this, mView);
        isViewCreated = true;
        initView();
        lazyLoad();
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            initData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }


    /**
     * fragment 的布局文件
     *
     * @return 布局文件的资源id
     */
    protected abstract int initLayout();

    /**
     * fragment 的布局文件中控件的初始化
     */
    protected abstract void initView();

    /**
     * 从网络获取数据
     */
    protected void initData() {

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

    protected void openActivity(Class clazz, String key, String value) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    protected void openActivity(Class clazz, HashMap<String, String> map) {
        Intent intent = new Intent(mActivity, clazz);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }

        startActivity(intent);
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    public void shotToast(String msg) {
        ToastUtils.showLongToast(mActivity.getApplicationContext(), msg);
    }

    public boolean isPageEndFlag() {
        return pageEndFlag;
    }

    public void setPageEndFlag(boolean pageEndFlag) {
        this.pageEndFlag = pageEndFlag;
    }
}
