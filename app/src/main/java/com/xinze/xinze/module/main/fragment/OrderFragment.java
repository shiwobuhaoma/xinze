package com.xinze.xinze.module.main.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.module.main.adapter.OrderRecycleViewAdapter;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.main.presenter.OrderPresenterImp;
import com.xinze.xinze.module.main.view.IOrderView;
import com.xinze.xinze.module.order.view.OrderDetailActivity;
import com.xinze.xinze.utils.MessageEvent;
import com.xinze.xinze.widget.SimpleToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.order_srl)
    SmartRefreshLayout mOrderSmartRefresh;
    private OrderPresenterImp opi;
    private int pageNo = 1;
    private SmartRefreshLayout layout;
    private OrderRecycleViewAdapter orva;
    private List<OrderItem> data;

    private static final String CLEAR_DATA = "clearData";

    /**
     * RecycleView条目被点击的位置
     */
    private int mPosition = 0;
    private LinearLayoutManager layoutManager;

//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }

    @Override
    protected void initData() {
        super.initData();
        if (App.mUser != null && App.mUser.isLogin()) {
            mOrderSmartRefresh.setEnableLoadMore(true);
            mOrderSmartRefresh.setEnableLoadMore(true);
            opi = new OrderPresenterImp(this, mActivity);
            opi.getOderList(1, 10);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_order;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        orderToolBar.setMainTitle(R.string.orderList);
        orderToolBar.setLeftTitleGone();
        orderToolBar.setTitleMarginTop();
        layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        orderRv.setLayoutManager(layoutManager);
        orva = new OrderRecycleViewAdapter(mActivity);
        orderRv.setAdapter(orva);
        orva.setOnItemClickListener(new OrderRecycleViewAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                mPosition = position;
                OrderItem orderItem = data.get(position);
                String orderId = orderItem.getOrderid();
                openActivity(OrderDetailActivity.class, "orderId", orderId);
            }
        });
        if (App.mUser != null && App.mUser.isLogin()) {
            mOrderSmartRefresh.setEnableRefresh(true);
            mOrderSmartRefresh.setEnableLoadMore(true);
        } else {
            mOrderSmartRefresh.setEnableLoadMore(false);
            mOrderSmartRefresh.setEnableLoadMore(false);
        }
        mOrderSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                if (App.mUser.isLogin()) {
                    opi.getOderList(pageNo, 10);
                } else {
                    refreshLayout.finishRefresh();
                }
            }
        });
        mOrderSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                if (App.mUser.isLogin()) {
                    opi.getOderList(pageNo, 10);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
        layout = mOrderSmartRefresh.getLayout();

    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }




    @Override
    public void getOrderListSuccess() {
        if (pageNo == 1) {
            layout.finishRefresh(2000);
        } else {
            layout.finishLoadMore(2000);
        }
    }

    @Override
    public void getOrderListFailed() {
        layout.finishRefresh(false);
    }

    public void refresh() {
        if (App.mUser != null && !App.mUser.isLogin() && orva != null) {
            orva.clearData();
        } else {
            opi = new OrderPresenterImp(this, mActivity);
            opi.getOderList(1, 10);
        }
    }

    public void clearData() {
        if (orva != null) {
            orva.clearData();
        }
    }


    public void upData(List<OrderItem> data) {
        this.data = data;
        if (orva != null){
            orva.setData(data);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void clear(MessageEvent messageEvent) {
        if (CLEAR_DATA.equals(messageEvent.getMessage())){
            if (orva != null) {
                orva.clearData();
            }
        }

    }
}
