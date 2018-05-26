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
import com.xinze.xinze.module.send.adapter.BillRecycleViewAdapter;
import com.xinze.xinze.module.send.view.IBillView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 普通货单
 * @author lxf
 */
public class OrdinaryBillFragment extends AbstractBillFragment implements IBillView{
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
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initView() {
        llm = new LinearLayoutManager(mActivity);
        ordinaryBillRv.setLayoutManager(llm);
        billRecycleViewAdapter = new BillRecycleViewAdapter(mActivity);
        ordinaryBillRv.setAdapter(billRecycleViewAdapter);
        ordinaryBillSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                bpi.getBillList(getBillType(), pageNo, pageSize,remarks);
            }
        });
        ordinaryBillSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                bpi.getBillList(getBillType(), pageNo, pageSize,remarks);
            }
        });
        billRecycleViewAdapter.setOnItemClickListener(new BillRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpToOrderDetailActivity(position);
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

    @Override
    protected int getBillType() {
        return 0;
    }

    @Override
    public void getBillsSuccess(String msg) {
        moveToPosition(llm,ordinaryBillRv,mPosition);
    }


}
