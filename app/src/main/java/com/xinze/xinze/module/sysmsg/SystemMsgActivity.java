package com.xinze.xinze.module.sysmsg;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.sysmsg.adapter.SystemMessageAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class SystemMsgActivity extends BaseActivity {

    private ArrayList<SystemMessage> listSysMsg = new ArrayList<>();
    @BindView(R.id.system_msg_rv)
    RecyclerView mSystemMsgRv;
    @BindView(R.id.system_msg_sl)
    SmartRefreshLayout mSystemMsgSl;

    @Override
    protected int initLayout() {
        return R.layout.system_msg_activity;
    }

    @Override
    protected void initView() {
        mSystemMsgRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mSystemMsgRv.setAdapter(new SystemMessageAdapter(this,listSysMsg));
        //设置 Header 为 贝塞尔雷达 样式
        mSystemMsgSl.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));

        //设置 Footer 为 球脉冲 样式
        mSystemMsgSl.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        mSystemMsgSl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        mSystemMsgSl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }


}
