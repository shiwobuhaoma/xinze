package com.xinze.xinze.module.invite.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.invite.adapter.DriverInviteRecycleViewAdapter;
import com.xinze.xinze.module.invite.model.TruckownerDriverVO;
import com.xinze.xinze.module.invite.presenter.InvitePresenterImp;

import java.util.List;

import butterknife.BindView;

/**
 * Created by feibai on 2018/5/14.
 * desc:司机邀请DriverInviteFragment
 */

public class DriverInviteFragment extends BaseFragment {
    @BindView(R.id.driver_invite_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.driver_invite_srl)
    SmartRefreshLayout mSmartRefreshLayout;


    protected SmartRefreshLayout layout;
    private int pageNo = AppConfig.PAGE_NO;
    private int pageSize = AppConfig.PAGE_SIZE;
    private boolean pageEndFlag = false;
    protected List<TruckownerDriverVO> data;
    protected DriverInviteRecycleViewAdapter mAdapter;
    protected InvitePresenterImp mPresenter;
    protected LinearLayoutManager llm;
    protected int mPosition = 0;
    protected String inviteFlag = null;

    @Override
    protected int initLayout() {
        {
            return R.layout.driver_invite_fragment;
        }
    }

    @Override
    protected void initView() {
        llm = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new DriverInviteRecycleViewAdapter(mActivity,this);
        mRecyclerView.setAdapter(mAdapter);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                mPresenter.getMyTruckOwnerInvitation( pageNo, pageSize,inviteFlag);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!pageEndFlag) {
                    pageNo++;
                    mPresenter.getMyTruckOwnerInvitation( pageNo, pageSize,inviteFlag);
                }else{
                    layout.finishLoadMore(1);
                    DriverInviteFragment.this.shotToast(AppConfig.LOAD_INFO_FINISH);
                }
            }
        });
/*        mAdapter.setOnItemClickListener(new BillRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //jumpToOrderDetailActivity(position);
            }
        });*/
        layout=mSmartRefreshLayout.getLayout();
    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter = new InvitePresenterImp(this);
        mPresenter.getMyTruckOwnerInvitation(pageNo, pageSize, inviteFlag);
    }

    public void setData(final List<TruckownerDriverVO> data) {
        if (data != null && data.size() > 0) {
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

    public void getInitDataFailed() {
        layout.finishRefresh(false);
    }

    public boolean isPageEndFlag() {
        return pageEndFlag;
    }

    public void setPageEndFlag(boolean pageEndFlag) {
        this.pageEndFlag = pageEndFlag;
    }

}
