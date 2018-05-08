package com.xinze.xinze.module.send.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.main.adapter.OrderRecycleViewAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 普通货单
 * @author lxf
 */
public class OrdinaryBillFragment extends BaseFragment {
    @BindView(R.id.ordinary_bill_rv)
    RecyclerView ordinaryBillRv;
    @BindView(R.id.ordinary_bill_srl)
    SmartRefreshLayout ordinaryBillSrl;
    @BindView(R.id.send_goods_from)
    TextView sendGoodsFrom;
    @BindView(R.id.send_goods_to)
    TextView sendGoodsTo;
    @Override
    protected int initLayout() {
        return R.layout.bill_ordinary_fragment;
    }

    @Override
    protected void initView() {
        LinearLayoutManager llm = new LinearLayoutManager(mActivity);
        ordinaryBillRv.setLayoutManager(llm);
        OrderRecycleViewAdapter orderRecycleViewAdapter = new OrderRecycleViewAdapter(mActivity);
        ordinaryBillRv.setAdapter(orderRecycleViewAdapter);
        ordinaryBillSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        ordinaryBillSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });

    }
    @OnClick({R.id.send_goods_from, R.id.send_goods_to})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_goods_from:
                break;
            case R.id.send_goods_to:
                break;
            default:
                break;
        }
    }

}
