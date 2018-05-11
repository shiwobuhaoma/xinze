package com.xinze.xinze.module.sysmsg;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.sysmsg.model.NotifyEntity;
import com.xinze.xinze.module.sysmsg.presenter.SystemMsgPresenterImp;
import com.xinze.xinze.module.sysmsg.view.ISystemMsgView;
import com.xinze.xinze.module.sysmsg.adapter.SystemMessageAdapter;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 系统通知消息
 * @author lxf
 */
public class SystemMsgActivity extends BaseActivity implements ISystemMsgView {

    private ArrayList<NotifyEntity> listSysMsg = new ArrayList<>();
    @BindView(R.id.system_msg_rv)
    RecyclerView mSystemMsgRv;
    @BindView(R.id.system_msg_srl)
    public SmartRefreshLayout mSystemMsgSmartRefresh;
    @BindView(R.id.system_msg_tool_bar)
    SimpleToolbar mSystemMsgTb;
    private LinearLayoutManager layoutManager;
    private SystemMessageAdapter systemMessageAdapter;
    private int pageNo = 1;
    private int pageSize = 10;
    private boolean pageEndFlag = false;
    private List<NotifyEntity> data;
    private SmartRefreshLayout layout;
    private int mPosition = 0;
    private SystemMsgPresenterImp opi;

    public boolean isPageEndFlag() {
        return pageEndFlag;
    }

    public void setPageEndFlag(boolean pageEndFlag) {
        this.pageEndFlag = pageEndFlag;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    protected int initLayout() {
        return R.layout.system_msg_activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.mUser.isLogin()){
            opi = new SystemMsgPresenterImp(this, this);
            opi.getSystemMsgList(1, 10);
        }
    }

    @Override
    protected void initView() {
        mSystemMsgTb.setMainTitle("系统消息");
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSystemMsgRv.setLayoutManager(layoutManager);
        SystemMsgPresenterImp paramOpi=new SystemMsgPresenterImp(this, this);
        systemMessageAdapter = new SystemMessageAdapter(this,listSysMsg,paramOpi);
        mSystemMsgRv.setAdapter(systemMessageAdapter);

        mSystemMsgSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                if (App.mUser.isLogin()) {
                    opi.getSystemMsgList(pageNo, pageSize);
                } else {
                    refreshLayout.finishRefresh();
                }
            }
        });

        mSystemMsgSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (App.mUser.isLogin()) {
                    pageNo++;
                    opi.getSystemMsgList(pageNo, pageSize);
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });




        layout = mSystemMsgSmartRefresh.getLayout();

/*        mSystemMsgRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
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
        });*/

    }


    public void setData(final List<NotifyEntity> data) {
        if (data != null && data.size()>0){
            if (pageNo == 1){
                this.data = data;
            }else{
                this.data.addAll(data);
            }
            systemMessageAdapter.setData(this.data);
        }
    }


    public void getOrderListSuccess() {
        if (pageNo == 1) {
            layout.finishRefresh(1000);
        } else {
            layout.finishLoadMore(500);
        }
    }


    public void getOrderListFailed() {
        layout.finishRefresh(false);
    }


}
