package com.xinze.xinze.module.trucks.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.bean.SpaceItemDecoration;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.add.view.AddMyCarActivity;
import com.xinze.xinze.module.distributive.view.DistributiveDriverActivity;
import com.xinze.xinze.module.trucks.adapter.MyTruckRecycleViewAdapter;
import com.xinze.xinze.module.trucks.model.MyTruckVO;
import com.xinze.xinze.module.trucks.model.TruckDriverVO;
import com.xinze.xinze.module.trucks.model.TruckEntity;
import com.xinze.xinze.module.trucks.presenter.IMyTruckPresenter;
import com.xinze.xinze.module.trucks.presenter.MyTruckPresenterImp;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @author feibai
 * @date 2018/5/22
 * desc: 我的车辆
 */
public class MyTruckActivity extends BaseActivity implements IMyTruckView{
    @BindView(R.id.my_truck_toolbar)
    SimpleToolbar mToolbar;
    @BindView(R.id.my_truck_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.my_truck_srl)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.my_truck_empty_ll)
    LinearLayout myTruckEmptyLinearLayout;
    @BindView(R.id.my_truck_add_bt)
    FloatingActionButton myTruckAddButton;

    private int pageNo = AppConfig.PAGE_NO;
    private int pageSize = AppConfig.PAGE_SIZE;
    private boolean pageEndFlag = false;
    protected List<MyTruckVO> data;
    protected MyTruckRecycleViewAdapter mAdapter;
    protected IMyTruckPresenter mPresenter;
    protected LinearLayoutManager llm;
    protected int mPosition = 0;
    protected String inviteFlag = null;

    /**
     * onResume是否刷新标志
     */
    public static Boolean isRefresh = false;


    @Override
    protected int initLayout() {
        return R.layout.my_truck_activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefresh) {
            mPresenter.myTrucks(pageNo, pageSize, AppConfig.YES);
        }
        isRefresh = false;
    }

    @Override
    protected void initView() {

        // 初始化标题栏
        initToolbar();
        // 初始化RecyclerView
        initRecyclerView();
        // 添加车辆点击事件
        myTruckAddButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FB9251")));
        myTruckAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(AddMyCarActivity.class);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MyTruckPresenterImp(this,this);
        mPresenter.myTrucks(pageNo, pageSize, AppConfig.YES);
        isRefresh = false;
    }

    /**
     * 初始化标题栏
     *
     * @author feibai
     */
    private void initToolbar() {
        mToolbar.setTitleMarginTop();
        mToolbar.setMainTitle(getString(R.string.my_truck_titile));
        mToolbar.setLeftTitleVisible();
        mToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * @author feibai
     */
    private void initRecyclerView() {
        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new MyTruckRecycleViewAdapter(this);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(25));
        mRecyclerView.setAdapter(mAdapter);
        // 绑定下拉刷新事件
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                mPresenter.myTrucks(pageNo, pageSize, AppConfig.YES);
            }
        });
        // 绑定上拉刷新加载更多事件
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!pageEndFlag) {
                    pageNo++;
                    mPresenter.myTrucks(pageNo, pageSize, AppConfig.YES);
                } else {
                    mSmartRefreshLayout.finishLoadMore(500);
                    MyTruckActivity.this.shotToast(AppConfig.LOAD_INFO_FINISH);
                }
            }
        });
        mAdapter.setOnItemClickListener(new MyTruckRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void click(View v, int position) {
                MyTruckVO myTruckVO = data.get(position);
                TruckEntity truck = myTruckVO.getTruck();
                TruckDriverVO truckDriver = myTruckVO.getTruckDriver();
                String truckId = truck.getId();
                String driverId = truckDriver.getDriverId();
                String rightFlag = truckDriver.getRightFlag();
                HashMap<String,String > map = new HashMap<>(3);
                map.put("truckId",truckId);
                map.put("driverId",driverId);
                map.put("rightFlag",rightFlag);
                openActivity(DistributiveDriverActivity.class,map);
            }

            @Override
            public void edit(int position) {
                MyTruckVO myTruckVO = data.get(position);
                TruckEntity truck = myTruckVO.getTruck();
                TruckDriverVO truckDriver = myTruckVO.getTruckDriver();
                String truckId = truck.getId();
                String driverId = truckDriver.getDriverId();
                String rightFlag = truckDriver.getRightFlag();
                String id = truckDriver.getId();
                HashMap<String,String > map = new HashMap<>(4);
                map.put("truckId",truckId);
                map.put("driverId",driverId);
                map.put("rightFlag",rightFlag);
                map.put("id",id);
                openActivity(DistributiveDriverActivity.class,map);
            }

            @Override
            public void delete(int position) {
                mPosition = position;
                mPresenter.delMyTruck(data.get(position).getTruckDriver().getDriverId());
            }
        });
    }

    public void setData(final List<MyTruckVO> data) {
        if (data != null && data.size() > 0) {
            myTruckEmptyLinearLayout.setVisibility(View.GONE);
            mSmartRefreshLayout.setVisibility(View.VISIBLE);
            if (pageNo == 1) {
                this.data = data;
            } else {
                this.data.addAll(data);
            }
            mAdapter.setData(this.data);
        }
    }

    public void getInitDataSuccess() {
        if (pageNo == 1) {
            mSmartRefreshLayout.finishRefresh(500);
        } else {
            mSmartRefreshLayout.finishLoadMore(500);
        }
    }

    /**
     * 当数据为空显示的说明页面
     *
     */
    public void showEmptyPage() {
        myTruckEmptyLinearLayout.setVisibility(View.VISIBLE);
        mSmartRefreshLayout.setVisibility(View.GONE);
    }

    /**
     * 重新刷新数据
     *
     */
    public void setRefreshData() {
        mPresenter.myTrucks(AppConfig.PAGE_NO, AppConfig.PAGE_SIZE, inviteFlag);
    }



    public IMyTruckPresenter getmPresenter() {
        return mPresenter;
    }

    @Override
    public void deleteMyTruckSuccess(String msg) {
        data.remove(mPosition);
        mAdapter.notifyItemChanged(mPosition);
    }

    @Override
    public void deleteMyTruckFailed(String msg) {
        RxToast.showToast(msg);
    }
}
