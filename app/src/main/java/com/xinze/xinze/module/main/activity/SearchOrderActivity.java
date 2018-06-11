package com.xinze.xinze.module.main.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.main.adapter.OrderRecycleViewAdapter;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.main.presenter.OrderPresenterImp;
import com.xinze.xinze.module.main.view.IOrderView;
import com.xinze.xinze.module.order.view.OrderDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索订单界面
 *
 * @author lxf
 */
public class SearchOrderActivity extends BaseActivity implements IOrderView {
    @BindView(R.id.activity_search_order_et)
    EditText mEditText;
    @BindView(R.id.activity_search_order_clear)
    ImageView activitySearchOrderClear;
    @BindView(R.id.activity_search_order_cancel)
    TextView activitySearchOrderCancel;
    @BindView(R.id.search_bar)
    RelativeLayout searchBar;
    @BindView(R.id.search_order_rv)
    RecyclerView searchOrderRv;
    @BindView(R.id.search_order_srl)
    SmartRefreshLayout searchOrderSrl;

    private List<OrderItem> mData;
    private OrderRecycleViewAdapter mAdapter;
    private OrderPresenterImp opi;
    private int pageNo = 1;
    private String remark;

    @Override
    protected int initLayout() {
        return R.layout.search_order_activity;
    }

    @Override
    protected void initView() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence keyWords, int arg1, int arg2, int arg3) {
                if (TextUtils.isEmpty(keyWords)) {
                    activitySearchOrderClear.setVisibility(View.GONE);
                    mAdapter.clearData();
                } else {
                    activitySearchOrderClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (App.mUser != null && App.mUser.isLogin()) {
                    searchOrderSrl.setEnableLoadMore(true);
                    opi = new OrderPresenterImp(SearchOrderActivity.this, SearchOrderActivity.this);
                    remark = editable.toString();
                    opi.getOderList(1, 10, remark);
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        searchOrderRv.setLayoutManager(layoutManager);
        mAdapter = new OrderRecycleViewAdapter(this);
        searchOrderRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OrderRecycleViewAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                OrderItem orderItem = mData.get(position);
                String orderId = orderItem.getOrderid();
                openActivity(OrderDetailActivity.class, "orderId", orderId);
            }
        });
        if (App.mUser != null && App.mUser.isLogin()) {
            searchOrderSrl.setEnableRefresh(true);
            searchOrderSrl.setEnableLoadMore(true);
        } else {
            searchOrderSrl.setEnableRefresh(false);
            searchOrderSrl.setEnableLoadMore(false);
        }
        searchOrderSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                if (App.mUser.isLogin()) {
                    if (!TextUtils.isEmpty(remark)){
                        opi.getOderList(pageNo, 10, remark);
                    }
                } else {
                    refreshLayout.finishRefresh();
                }
            }
        });
        searchOrderSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                if (App.mUser.isLogin()) {
                    if (!TextUtils.isEmpty(remark)){
                        opi.getOderList(pageNo, 10, remark);
                    }
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }

    @OnClick({R.id.activity_search_order_clear, R.id.activity_search_order_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_search_order_clear:
                mEditText.setText("");
                break;
            case R.id.activity_search_order_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void getOrderListSuccess() {
        searchOrderSrl.setVisibility(View.VISIBLE);
        if (pageNo == 1) {
            searchOrderSrl.finishRefresh(2000);
        } else {
            searchOrderSrl.finishLoadMore(2000);
        }
    }

    @Override
    public void getOrderListFailed() {
        if (pageNo == 1) {
            searchOrderSrl.finishRefresh(2000);
        } else {
            searchOrderSrl.finishLoadMore(2000);
        }
    }

    @Override
    public void upData(List<OrderItem> data) {
        this.mData = data;
        if (mAdapter != null) {
            mAdapter.setData(data);
        }
    }

    @Override
    public void clearData() {
        if (mAdapter != null) {
            mAdapter.clearData();
        }
    }
}
