package com.xinze.xinze.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;

import butterknife.BindView;

/**
 * 首页
 *
 * @author lxf
 * Created by lxf on 2016/5/15.
 */
public class OrderFragment extends BaseFragment {

    @BindView(R.id.order_rv)
    RecyclerView orderRv;

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_order;
    }

    @Override
    protected void initView() {

    }


    public static OrderFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
