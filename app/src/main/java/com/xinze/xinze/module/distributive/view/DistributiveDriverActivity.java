package com.xinze.xinze.module.distributive.view;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.distributive.adapter.DistributiveDriverAdapter;
import com.xinze.xinze.module.distributive.presenter.DistributiveDriverPresenter;
import com.xinze.xinze.module.distributive.view.IDistributiveDriverView;
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
    private DistributiveDriverPresenter ddp;
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
        distributiveRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        distributiveRv.setAdapter(dda);
        dda.setOnItemClickListener(new DistributiveDriverAdapter.OnItemClickListener() {
            @Override
            public void click(View view, int position) {
                if ( mData.get(position).isChecked()){
                    mData.get(position).setChecked(false);
                }else{
                    mData.get(position).setChecked(true);
                }
                dda.setData(mData);
            }

            @Override
            public void call(int position) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mData.get(position).getDriverMobile())));
            }
        });
        ddp = new DistributiveDriverPresenter(this, this);
        ddp.getMyTruckDrivers(pageNo,pageSize,"1");
        distributiveSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                ddp.getMyTruckDrivers(pageNo, pageSize, AppConfig.YES);
            }
        });
        // 绑定上拉刷新加载更多事件
        distributiveSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!pageEndFlag) {
                    pageNo++;
                    ddp.getMyTruckDrivers(pageNo, pageSize, AppConfig.YES);
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
        ddp.appointDriver4Truck(truckId, driverId, rightFlag, id);
    }

    @Override
    public void appointDriver4TruckSuccess(String msg) {

    }

    @Override
    public void appointDriver4TruckFailed(String msg) {

    }

    public void setData(List<TruckownerDriverVO> data) {
        this.mData = data;
        dda.setData(data);
    }
}
