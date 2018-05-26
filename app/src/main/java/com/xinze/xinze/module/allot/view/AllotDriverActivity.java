package com.xinze.xinze.module.allot.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.bean.SpaceItemDecoration;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.allot.adapter.AllotDriverAdapter;
import com.xinze.xinze.module.allot.presenter.AllotDriverPresenterImp;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分配司机
 *
 * @author lxf
 */
public class AllotDriverActivity extends BaseActivity implements IAllotDriverView, AllotDriverAdapter.OnClickListener {
    @BindView(R.id.allot_driver_toolbar)
    SimpleToolbar mToolbar;
    @BindView(R.id.allot_driver_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.allot_driver_srl)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.allot_driver_bt)
    Button allotDriverBt;


    protected LinearLayoutManager llm;
    private int pageNo = AppConfig.PAGE_NO;
    private int pageSize = AppConfig.PAGE_SIZE;
    private AllotDriverPresenterImp mPresenter;
    /**
     * onResume是否刷新标志
     */
    public static Boolean isRefresh = false;
    private AllotDriverAdapter mAdapter;
    protected List<TruckownerDriverVO> data;
    private String rightFlag;
    private String driverUserId;
    private String truckId;
    private String driverId;


    @Override
    protected int initLayout() {
        return R.layout.allot_driver_activity;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null){
            truckId = intent.getStringExtra("truckId");
            driverId = intent.getStringExtra("driverId");
        }
        // 初始化标题栏
        initToolbar();
        // 初始化RecyclerView
        initRecyclerView();
        // 添加司机btn点击事件
        allotDriverBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MyDriverActivity.this,DriverAddActivity.class));
            }
        });
    }

    private void initRecyclerView() {
        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new AllotDriverAdapter(this);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(30));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        // 绑定下拉刷新事件
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                mPresenter.getMyTruckDrivers(pageNo, pageSize, AppConfig.YES);
            }
        });
        // 绑定上拉刷新加载更多事件
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                if (!pageEndFlag) {
                pageNo++;
                mPresenter.getMyTruckDrivers(pageNo, pageSize, AppConfig.YES);
//                } else {
//                    layout.finishLoadMore(500);
//                    RxToast.showToast(AppConfig.LOAD_INFO_FINISH);
//                }
            }
        });
    }

    private void initToolbar() {
        mToolbar.setMainTitle(getString(R.string.allot_driver_title));
        mToolbar.setLeftTitleVisible();
        mToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new AllotDriverPresenterImp(this, this);
        mPresenter.getMyTruckDrivers(pageNo, pageSize, AppConfig.YES);
        isRefresh = false;
    }

    @OnClick(R.id.allot_driver_bt)
    public void onClick() {
        mPresenter.truckAllotDriver(truckId,driverId,rightFlag,driverUserId);
    }

    @Override
    public void getMyTruckDriversSuccess(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void getMyTruckDriversFailed(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void truckAllotDriverSuccess(String msg) {
        RxToast.showToast(msg);
        finish();
    }

    @Override
    public void truckAllotDriverFailed(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }


    @Override
    public void onClickView(View v, int position) {
        TruckownerDriverVO truckownerDriverVO = data.get(position);
        if (v.getId() != R.id.allot_driver_phone) {
            if ("1".equals( truckownerDriverVO.getRightFlag())){
                rightFlag = truckownerDriverVO.getRightFlag();
                driverUserId = truckownerDriverVO.getDriverUserId();
                truckownerDriverVO.setChecked(!truckownerDriverVO.isChecked());
                mAdapter.notifyItemChanged(position);
            }else{
                //TODO 不允许抢单
                RxToast.showToast("该司机没有抢单权限");
            }


        } else {

            String driverMobile = truckownerDriverVO.getDriverMobile();
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + driverMobile)));

        }

    }

    public void setData(final List<TruckownerDriverVO> data) {
        if (data != null && data.size() > 0) {
            if (pageNo == 1) {
                this.data = data;
                mSmartRefreshLayout.finishRefresh();
            } else {
                this.data.addAll(data);
                mSmartRefreshLayout.finishLoadMore();
            }
            mAdapter.setData(this.data);
        } else {
            if (pageNo == 1) {
                mSmartRefreshLayout.finishRefresh();
            } else {
                mSmartRefreshLayout.finishLoadMore();
            }
        }


    }
}
