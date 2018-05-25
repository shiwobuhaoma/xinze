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
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.bean.SpaceItemDecoration;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.trucks.adapter.MyTruckRecycleViewAdapter;
import com.xinze.xinze.module.trucks.model.MyTruckVO;
import com.xinze.xinze.module.trucks.presenter.IMyTruckPresenter;
import com.xinze.xinze.module.trucks.presenter.MyTruckPresenterImp;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.List;

import butterknife.BindView;

/**
 * @author feibai
 * @date 2018/5/22
 * desc: MyTruckActivity
 */
public class MyTruckActivity extends BaseActivity {
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

    protected SmartRefreshLayout layout;
    private int pageNo = AppConfig.PAGE_NO;
    private int pageSize = AppConfig.PAGE_SIZE;
    private boolean pageEndFlag = false;
    protected List<MyTruckVO> data;
    protected MyTruckRecycleViewAdapter mAdapter;
    protected IMyTruckPresenter mPresenter;
    protected LinearLayoutManager llm;
    protected int mPosition = 0;
    protected String inviteFlag = null;
    // onResume是否刷新标志
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
                //startActivity(new Intent(MyTruckActivity.this, TruckAddActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MyTruckPresenterImp(this);
        mPresenter.myTrucks(pageNo, pageSize, AppConfig.YES);
        isRefresh = false;
    }

    /**
     * 初始化标题栏
     *
     * @author feibai
     * @time 2018/5/17  21:48
     * @desc
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
     * @time 2018/5/17  22:25
     * @desc
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
                    layout.finishLoadMore(500);
                    MyTruckActivity.this.shotToast(AppConfig.LOAD_INFO_FINISH);
                }
            }
        });
        layout = mSmartRefreshLayout.getLayout();
    }

    public void setData(final List<MyTruckVO> data) {
        if (data != null && data.size() > 0) {
            myTruckEmptyLinearLayout.setVisibility(View.GONE);
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
            layout.finishRefresh(500);
        } else {
            layout.finishLoadMore(500);
        }
    }

    /**
     * 当数据为空显示的说明页面
     *
     * @author feibai
     * @time 2018/5/18  19:42
     * @desc
     */
    public void showEmptyPage() {
        myTruckEmptyLinearLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 重新刷新数据
     *
     * @author feibai
     * @time 2018/5/19  19:27
     * @desc
     */
    public void setRefreshData() {
        mPresenter.myTrucks(AppConfig.PAGE_NO, AppConfig.PAGE_SIZE, inviteFlag);
    }

    public boolean isPageEndFlag() {
        return pageEndFlag;
    }

    public void setPageEndFlag(boolean pageEndFlag) {
        this.pageEndFlag = pageEndFlag;
    }

    public IMyTruckPresenter getmPresenter() {
        return mPresenter;
    }

}
