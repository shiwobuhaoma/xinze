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
import com.xinze.xinze.config.OrderConfig;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.send.adapter.BillRecycleViewAdapter;
import com.xinze.xinze.module.send.view.IBillView;
import com.xinze.xinze.widget.SelectAddressView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 普通货单
 *
 * @author lxf
 */
public class OrdinaryBillFragment extends AbstractBillFragment implements IBillView {
    @BindView(R.id.ordinary_bill_rv)
    RecyclerView ordinaryBillRv;
    @BindView(R.id.ordinary_bill_srl)
    SmartRefreshLayout ordinaryBillSrl;
    @BindView(R.id.send_goods_from)
    TextView sendGoodsFrom;
    @BindView(R.id.send_goods_to)
    TextView sendGoodsTo;
    @BindView(R.id.send_goods_select_from)
    SelectAddressView sendGoodsSelectFrom;

    private int mCurrentView;
    private String fromID;
    private String toID;

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
                bpi.getBillList(getBillType(), pageNo, pageSize, remarks);
            }
        });
        ordinaryBillSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                bpi.getBillList(getBillType(), pageNo, pageSize, remarks);
            }
        });
        billRecycleViewAdapter.setOnItemClickListener(new BillRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpToOrderDetailActivity(position);
            }
        });
        sendGoodsSelectFrom.setmOnSelectAddressListener(new SelectAddressView.OnSelectAddressListener() {
            @Override
            public void selectAddress(String name, String id) {
                switch (mCurrentView) {
                    case R.id.send_goods_from:
                        fromID = id;
                        sendGoodsFrom.setText(name);
                        break;
                    case R.id.send_goods_to:
                        toID = id;
                        sendGoodsTo.setText(name);
                        bpi.searchRouteList(fromID,toID,pageNo,pageSize);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.send_goods_from, R.id.send_goods_to})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_goods_from:
                mCurrentView = R.id.send_goods_from;
                billRecycleViewAdapter.clearData();
                sendGoodsSelectFrom.setViewVisible();
                break;
            case R.id.send_goods_to:
                mCurrentView = R.id.send_goods_to;
                sendGoodsSelectFrom.setViewVisible();
                break;
            default:
                break;
        }
    }

    @Override
    protected int getBillType() {
        //普通货单
        return OrderConfig.ORDINARYBILL;
    }

    @Override
    public void getBillsSuccess(String msg) {
        moveToPosition(llm, ordinaryBillRv, mPosition);
    }

    @Override
    public void searchRouteListSuccess(String msg) {
        sendGoodsSelectFrom.setViewGone();
    }

    @Override
    public void searchRouteListFailed(String msg) {
        sendGoodsSelectFrom.setViewGone();
    }

    @Override
    public void setOrderItemData(List<OrderItem> data) {
        super.setOrderItemData(data);
        billRecycleViewAdapter.setData(data);
    }
}
