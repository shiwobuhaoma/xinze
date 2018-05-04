package com.xinze.xinze.module.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.find.FindGoodsActivity;
import com.xinze.xinze.module.main.adapter.OrderRecycleViewAdapter;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.main.presenter.OrderPresenterImp;
import com.xinze.xinze.module.main.view.IOrderView;
import com.xinze.xinze.module.order.OrderDetailActivity;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.List;

import butterknife.BindView;

/**
 * 首页
 *
 * @author lxf
 * Created by lxf on 2016/5/15.
 */
public class OrderFragment extends BaseFragment implements IOrderView {

    @BindView(R.id.order_rv)
    RecyclerView orderRv;
    @BindView(R.id.order_tool_bar)
    SimpleToolbar orderToolBar;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.order_search_bar)
    RelativeLayout orderSearchBar;

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_order;
    }

    @Override
    protected void initView() {
        orderToolBar.setMainTitle(R.string.orderList);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.mUser.isLogin()){
            OrderPresenterImp opi = new OrderPresenterImp(this, mActivity);
            opi.getOderList(1,10);
        }
    }

    public static OrderFragment newInstance() {


        return new OrderFragment();
    }


    public void setData(final List<OrderItem> data) {
        OrderRecycleViewAdapter orva = new OrderRecycleViewAdapter(mActivity, data);
        orderRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        orderRv.setAdapter(orva);
        orva.setOnItemClickListener(new OrderRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                OrderItem orderItem = data.get(position);
                String orderId = orderItem.getOrderid();
                openActivity(OrderDetailActivity.class,"orderId",orderId);
            }
        });
    }

    @Override
    public void getOrderListSuccess() {

    }

    @Override
    public void getOrderListFailed() {

    }

    @Override
    public void shotToast(String msg) {

    }
}
