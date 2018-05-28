package com.xinze.xinze.module.send.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.order.view.OrderDetailActivity;
import com.xinze.xinze.module.send.adapter.BillRecycleViewAdapter;
import com.xinze.xinze.module.send.presenter.BillPresenterImp;
import com.xinze.xinze.module.send.view.IBillView;
import com.xinze.xinze.module.transport.view.TransportDetailsActivity;

import java.util.List;

/**
 * @author lxf
 * 抽象的父类
 */
public abstract class AbstractBillFragment extends BaseFragment implements IBillView {
    protected SmartRefreshLayout layout;
    protected int pageNo = 1;
    protected int pageSize = 10;
    protected List<OrderItem> data;
    protected BillRecycleViewAdapter billRecycleViewAdapter;
    protected BillPresenterImp bpi;
    protected LinearLayoutManager llm;
    protected int mPosition = 0;
    protected String remarks;

    @Override
    protected void initData() {
        super.initData();
        initData(getBillType());
    }

    /**
     * 获取订单类型
     * @return 返回订单类型
     */
    protected abstract int getBillType();


    protected void initData(int billType) {
        super.initData();
        bpi = new BillPresenterImp(this, mActivity);
        bpi.getBillList(billType, pageNo, pageSize,remarks);
    }
    public void setData(final List<OrderItem> data) {
        if (data != null && data.size() > 0) {
            if (pageNo == 1) {
                this.data = data;
            } else {
                this.data.addAll(data);
            }
            billRecycleViewAdapter.setData(this.data);
        }
    }

    @Override
    public void getBillsSuccess(String msg) {
        if (pageNo == 1) {
            layout.finishRefresh(2000);
        } else {
            layout.finishLoadMore(2000);
        }
    }

    @Override
    public void getBillsFailed(String msg) {
        layout.finishRefresh(false);
    }

    protected void jumpToOrderDetailActivity(int position) {
        mPosition = position;
        OrderItem orderItem = data.get(position);
        String orderId = orderItem.getId();
        openActivity(TransportDetailsActivity.class, "orderId", orderId);
    }

    public void setOrderItemData(List<OrderItem> data) {

    }
}
