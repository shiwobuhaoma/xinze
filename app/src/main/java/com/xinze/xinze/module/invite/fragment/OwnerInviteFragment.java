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
import com.xinze.xinze.module.invite.adapter.OwnerInviteRecycleViewAdapter;
import com.xinze.xinze.module.invite.model.OwnerDriverVO;
import com.xinze.xinze.module.invite.presenter.InvitePresenterImp;

import java.util.List;

import butterknife.BindView;

/**
 * Created by feibai on 2018/5/14.
 * desc:货主邀请DriverInviteFragment
 */

public class OwnerInviteFragment extends BaseFragment {
    @BindView(R.id.owner_invite_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.owner_invite_srl)
    SmartRefreshLayout mSmartRefreshLayout;

    protected SmartRefreshLayout layout;
    private int pageNo = AppConfig.PAGE_NO;
    private int pageSize = AppConfig.PAGE_SIZE;
    private boolean pageEndFlag = false;
    protected List<OwnerDriverVO> data;
    protected OwnerInviteRecycleViewAdapter mAdapter;
    protected InvitePresenterImp mPresenter;
    protected LinearLayoutManager llm;
    protected int mPosition = 0;
    protected String inviteFlag = null;
    // onResume是否刷新标志
    public static Boolean isRefresh = false;

    @Override
    protected int initLayout() {
        return R.layout.owner_invite_fragment;
    }


    @Override
    protected void initView() {
        llm = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new OwnerInviteRecycleViewAdapter(mActivity, this);
        mRecyclerView.setAdapter(mAdapter);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                mPresenter.getMyOwnerInvitation(pageNo, pageSize, inviteFlag);
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!pageEndFlag) {
                    pageNo++;
                    mPresenter.getMyOwnerInvitation(pageNo, pageSize, inviteFlag);
                } else {
                    layout.finishLoadMore(1);
                    OwnerInviteFragment.this.shotToast(AppConfig.LOAD_INFO_FINISH);
                }
            }
        });

        layout = mSmartRefreshLayout.getLayout();
    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter = new InvitePresenterImp(this);
        mPresenter.getMyOwnerInvitation(pageNo, pageSize, inviteFlag);
        isRefresh = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefresh) {
            mPresenter.getMyOwnerInvitation(pageNo, pageSize, inviteFlag);
        }
        isRefresh = false;
    }

    public void setData(final List<OwnerDriverVO> data) {
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
