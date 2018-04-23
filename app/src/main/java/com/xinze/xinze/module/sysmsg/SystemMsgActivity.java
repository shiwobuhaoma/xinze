package com.xinze.xinze.module.sysmsg;

import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;

import butterknife.BindView;

public class SystemMsgActivity extends BaseActivity {


    @BindView(R.id.system_msg_rv)
    RecyclerView mSystemMsgRv;
    @BindView(R.id.system_msg_sl)
    SmartRefreshLayout mSystemMsgSl;

    @Override
    protected int initLayout() {
        return R.layout.activity_system_msg;
    }

    @Override
    protected void initView() {
        final RefreshLayout refreshLayout = (RefreshLayout)mSystemMsgSl;
//设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
//设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
    }


}
