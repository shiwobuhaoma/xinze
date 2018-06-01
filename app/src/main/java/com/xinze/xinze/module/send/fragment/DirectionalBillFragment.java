package com.xinze.xinze.module.send.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.R;
import com.xinze.xinze.config.OrderConfig;
import com.xinze.xinze.module.send.adapter.BillRecycleViewAdapter;

import butterknife.BindView;

/**
 * 定向货单
 *
 * @author lxf
 */
public class DirectionalBillFragment extends AbstractBillFragment implements View.OnClickListener {
    @BindView(R.id.bill_search_bar)
    RelativeLayout billSearchBar;
    @BindView(R.id.directional_bill_rv)
    RecyclerView directionalBillRv;
    @BindView(R.id.directional_bill_srl)
    SmartRefreshLayout directionalBillSrl;


    @Override
    protected int initLayout() {
        return R.layout.bill_directional_fragment;
    }

    @Override
    protected void initView() {
        billSearchBar.setOnClickListener(this);
        llm = new LinearLayoutManager(mActivity);
        directionalBillRv.setLayoutManager(llm);
        billRecycleViewAdapter = new BillRecycleViewAdapter(mActivity);
        directionalBillRv.setAdapter(billRecycleViewAdapter);
        billRecycleViewAdapter.setOnItemClickListener(new BillRecycleViewAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void jumpSelectCar( int position) {
               jumpToSelectActivity(position);
            }

            @Override
            public void jumpDetails(int position) {
                jumpToOrderDetailActivity(position);
            }
        });
        layout = directionalBillSrl;
        directionalBillSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                bpi.getBillList(getBillType(), pageNo, pageSize, remarks);
            }
        });
        directionalBillSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                bpi.getBillList(getBillType(), pageNo, pageSize, remarks);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getBillType() {
        //定向货单
        return OrderConfig.DIRECTIONALBILL;
    }

    @Override
    public void getBillsSuccess(String msg) {
        super.getBillsSuccess(msg);
        moveToPosition(llm, directionalBillRv, mPosition);

    }


}
