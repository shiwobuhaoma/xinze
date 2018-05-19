package com.xinze.xinze.module.drivers.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.bean.SpaceItemDecoration;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.drivers.adapter.MyDriverRecycleViewAdapter;
import com.xinze.xinze.module.drivers.presenter.MyDriverPresenterImp;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.List;

import butterknife.BindView;

/**
 * @author feibai
 * @date 2018/5/17
 * desc:
 */
public class MyDriverActivity extends BaseActivity {

    @BindView(R.id.my_driver_toolbar)
    SimpleToolbar mToolbar;
    @BindView(R.id.my_driver_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.my_driver_srl)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.my_driver_empty_ll)
    LinearLayout myDriverEmptyLinearLayout;
    @BindView(R.id.my_driver_add_bt)
    Button myDriverAddButton;


    protected SmartRefreshLayout layout;
    private int pageNo = AppConfig.PAGE_NO;
    private int pageSize = AppConfig.PAGE_SIZE;
    private boolean pageEndFlag = false;
    protected List<TruckownerDriverVO> data;
    protected MyDriverRecycleViewAdapter mAdapter;
    protected MyDriverPresenterImp mPresenter;
    protected LinearLayoutManager llm;
    protected int mPosition = 0;
    protected String inviteFlag = null;
    // onResume是否刷新标志
    public static Boolean isRefresh = false;


    @Override
    protected int initLayout() {
        return R.layout.my_driver_activity;
    }

    @Override
    protected void initView() {
        // 初始化标题栏
        initToolbar();
        // 初始化RecyclerView
        initRecyclerView();
    }

    /**
     * @author feibai
     * @time 2018/5/17  22:25
     * @desc
     */
    private void initRecyclerView() {
        llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new MyDriverRecycleViewAdapter(this);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(30));
        mRecyclerView.setAdapter(mAdapter);
        // 绑定下拉刷新事件
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                mPresenter.myTruckDrivers(pageNo, pageSize, inviteFlag);
            }
        });
        // 绑定上拉刷新加载更多事件
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!pageEndFlag) {
                    pageNo++;
                    mPresenter.myTruckDrivers(pageNo, pageSize, inviteFlag);
                } else {
                    layout.finishLoadMore(1);
                    MyDriverActivity.this.shotToast(AppConfig.LOAD_INFO_FINISH);
                }
            }
        });
        layout = mSmartRefreshLayout.getLayout();
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
        mToolbar.setMainTitle(getString(R.string.my_driver_title));
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
        mPresenter = new MyDriverPresenterImp(this);
        mPresenter.myTruckDrivers(pageNo, pageSize, inviteFlag);
        isRefresh = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefresh) {
            mPresenter.myTruckDrivers(pageNo, pageSize, inviteFlag);
        }
        isRefresh = false;
    }


    public void setData(final List<TruckownerDriverVO> data) {
        if (data != null && data.size() > 0) {
            myDriverEmptyLinearLayout.setVisibility(View.GONE);
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
     *  当数据为空显示的说明页面
     *  @author feibai
     *  @time 2018/5/18  19:42
     *  @desc
     */
    public void showEmptyPage(){
        myDriverEmptyLinearLayout.setVisibility(View.VISIBLE);
    }

    public boolean isPageEndFlag() {
        return pageEndFlag;
    }

    public void setPageEndFlag(boolean pageEndFlag) {
        this.pageEndFlag = pageEndFlag;
    }
}
