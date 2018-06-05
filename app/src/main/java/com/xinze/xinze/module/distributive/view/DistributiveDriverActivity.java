package com.xinze.xinze.module.distributive.view;

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
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.distributive.adapter.DistributiveDriverAdapter;
import com.xinze.xinze.module.distributive.presenter.DistributiveDriverPresenter;
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
public class DistributiveDriverActivity extends BaseActivity implements IDistributiveDriverView {
    @BindView(R.id.distributive_toolbar)
    SimpleToolbar distributiveToolbar;
    @BindView(R.id.distributive_rv)
    RecyclerView distributiveRv;
    @BindView(R.id.distributive_srl)
    SmartRefreshLayout distributiveSrl;
    @BindView(R.id.distributive_bt)
    Button distributiveBt;
    private DistributiveDriverAdapter dda;
    private DistributiveDriverPresenter mPresenter;
    private String rightFlag;
    private String driverId;
    private String truckId;
    /**
     * 非必填,修改更新分配司机信息需填
     */
    private String id;
    private int pageNo = AppConfig.PAGE_NO;
    private int pageSize = AppConfig.PAGE_SIZE;
    private List<TruckownerDriverVO> mData;


    @Override
    protected int initLayout() {
        return R.layout.distributive_driver_activity;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            rightFlag = intent.getStringExtra("rightFlag");
            driverId = intent.getStringExtra("driverId");
            truckId = intent.getStringExtra("truckId");
            id = intent.getStringExtra("id");
        }
        initToolBar();
        dda = new DistributiveDriverAdapter(this);
        distributiveRv.setLayoutManager(new LinearLayoutManager(this));
        distributiveRv.setAdapter(dda);
        dda.setOnItemClickListener(new DistributiveDriverAdapter.OnItemClickListener() {
            @Override
            public void click(View view, int position) {

                for (int i = 0; i < mData.size(); i++) {
                    final TruckownerDriverVO truckownerDriverVO = mData.get(i);
                    if (i == position) {
                        if (truckownerDriverVO.isChecked()) {
                            truckownerDriverVO.setChecked(false);
                        } else {
                            truckownerDriverVO.setChecked(true);
                        }
                    } else {
                        truckownerDriverVO.setChecked(false);
                    }
                }
                dda.setData(mData);
            }

            @Override
            public void call(int position) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mData.get(position).getDriverMobile())));
            }

            @Override
            public void allowRobbing(int position) {
                for (int i = 0; i < mData.size(); i++) {
                    final TruckownerDriverVO truckownerDriverVO = mData.get(i);
                    if (position == i) {
                        truckownerDriverVO.setRightFlag("1");
                        rightFlag = "1";
                    } else {
                        truckownerDriverVO.setRightFlag("0");
                        rightFlag = "0";
                    }
                }
                dda.setData(mData);
            }
        });
        mPresenter = new DistributiveDriverPresenter(this, this);
        mPresenter.getMyTruckDrivers(pageNo, pageSize, "1");
        distributiveSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                mPresenter.getMyTruckDrivers(pageNo, pageSize, AppConfig.YES);
            }
        });
        // 绑定上拉刷新加载更多事件
        distributiveSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!pageEndFlag) {
                    pageNo++;
                    mPresenter.getMyTruckDrivers(pageNo, pageSize, AppConfig.YES);
                } else {
                    distributiveSrl.finishLoadMore(500);
                    shotToast(AppConfig.LOAD_INFO_FINISH);
                }
            }
        });
    }

    private void initToolBar() {
        distributiveToolbar.setLeftTitleVisible();
        distributiveToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        distributiveToolbar.setMainTitle("分配司机");
    }

    @OnClick(R.id.distributive_bt)
    public void onClick() {
        mPresenter.appointDriver4Truck(truckId, driverId, rightFlag, id);
    }

    @Override
    public void appointDriver4TruckSuccess(String msg) {
        RxToast.showToast(msg);
        finish();
    }

    @Override
    public void appointDriver4TruckFailed(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void getMyTruckDriversSuccess(String msg) {
        if (pageNo == 1) {
            distributiveSrl.finishRefresh();
        } else {
            distributiveSrl.finishLoadMore();
        }

    }

    @Override
    public void getMyTruckDriversFailed(String msg) {
        if (pageNo == 1) {
            distributiveSrl.finishRefresh();
        } else {
            distributiveSrl.finishLoadMore();
        }
    }

    public void setData(List<TruckownerDriverVO> data) {
        if (data == null) {
            setPageEndFlag(true);
        } else {
            if (pageNo == 1) {
                this.mData = data;
            } else {
                this.mData.addAll(data);
            }
            for (TruckownerDriverVO truckownerDriverVO : mData) {
                if (truckownerDriverVO.getDriverUserId().equals(driverId)) {
                    truckownerDriverVO.setChecked(true);
                }
                if ("1".equals(truckownerDriverVO.getRightFlag())) {
                    truckownerDriverVO.setRightFlag("1");
                }

            }
            dda.setData(mData);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.onDestroy();
        }
    }
}
