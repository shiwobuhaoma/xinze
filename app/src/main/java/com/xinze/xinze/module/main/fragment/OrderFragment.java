package com.xinze.xinze.module.main.fragment;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseFragment;
import com.xinze.xinze.config.AppConfig;
import com.xinze.xinze.module.main.activity.SearchOrderActivity;
import com.xinze.xinze.module.main.adapter.OrderRecycleViewAdapter;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.main.presenter.OrderPresenterImp;
import com.xinze.xinze.module.main.view.IOrderView;
import com.xinze.xinze.module.order.view.OrderDetailActivity;
import com.xinze.xinze.utils.MessageEvent;
import com.xinze.xinze.utils.UIUtils;
import com.xinze.xinze.widget.SimpleToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 首页
 *
 * @author lxf
 * Created by lxf on 2016/5/15.
 */
public class OrderFragment extends BaseFragment implements IOrderView {

    @BindView(R.id.order_rv)
    RecyclerView orderRv;
    @BindView(R.id.order_tool_bar)
    SimpleToolbar orderToolBar;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.order_search_bar)
    RelativeLayout orderSearchBar;
    @BindView(R.id.order_srl)
    SmartRefreshLayout mOrderSmartRefresh;
    private OrderPresenterImp opi;
    private int pageNo = 1;
    private OrderRecycleViewAdapter mAdapter;
    private List<OrderItem> data;

    private ValueAnimator mUpAnim;
    private ValueAnimator mDownAnim;

    /**
     * RecycleView条目被点击的位置
     */
    private int mPosition = 0;
    private LinearLayoutManager layoutManager;


    @Override
    protected void initData() {
        super.initData();
        if (App.mUser != null && App.mUser.isLogin()) {
            mOrderSmartRefresh.setEnableLoadMore(true);
            opi = new OrderPresenterImp(this, mActivity);
            opi.getOderList(1, 10,"");
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.main_fragment_order;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        orderToolBar.setMainTitle(R.string.orderList);
        orderToolBar.setLeftTitleGone();
        orderToolBar.setTitleMarginTop();
        orderSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 上滑动画
                if (mUpAnim == null) {
                    initUpAnim();
                }
                mUpAnim.start();
            }
        });
        layoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        orderRv.setLayoutManager(layoutManager);
        mAdapter = new OrderRecycleViewAdapter(mActivity);
        orderRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OrderRecycleViewAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                mPosition = position;
                OrderItem orderItem = data.get(position);
                String orderId = orderItem.getOrderid();
                openActivity(OrderDetailActivity.class, "orderId", orderId);
            }
        });
        if (App.mUser != null && App.mUser.isLogin()) {
            mOrderSmartRefresh.setEnableRefresh(true);
            mOrderSmartRefresh.setEnableLoadMore(true);
        } else {
            mOrderSmartRefresh.setEnableRefresh(false);
            mOrderSmartRefresh.setEnableLoadMore(false);
        }
        mOrderSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                if (App.mUser.isLogin()) {
                    opi.getOderList(pageNo, 10,"");
                } else {
                    refreshLayout.finishRefresh();
                }
            }
        });
        mOrderSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                if (App.mUser.isLogin()) {
                    opi.getOderList(pageNo, 10,"");
                } else {
                    refreshLayout.finishLoadMore();
                }
            }
        });

    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 恢复标题栏
        if (mView.getScrollY() != 0) {
            mView.post(() -> {
                if (mDownAnim == null) {
                    initDownAnim();
                }
                mDownAnim.start();
            });
        }
    }

    /**
     * 初始化SearchBar上滑的动画
     */
    private void initUpAnim() {
        mUpAnim = ValueAnimator.ofInt(0, orderSearchBar.getHeight()+ UIUtils.dip2px(20));
        mUpAnim.setDuration(250);
        mUpAnim.addUpdateListener(animation -> mView.scrollTo(0, (Integer)animation.getAnimatedValue()));
        mUpAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                openActivity(SearchOrderActivity.class);
            }

            @Override
            public void onAnimationCancel(Animator animation) {}
        });
        mUpAnim.setInterpolator(new LinearInterpolator());
    }

    /**
     * 初始化SearchBar下滑的动画
     */
    private void initDownAnim() {
        mDownAnim = ValueAnimator.ofInt(orderSearchBar.getHeight(), 0);
        mDownAnim.setDuration(300);
        mDownAnim.addUpdateListener(animation -> mView.scrollTo(0, (Integer)animation.getAnimatedValue()));
        mDownAnim.setInterpolator(new LinearInterpolator());
    }

    @Override
    public void getOrderListSuccess() {
        if (pageNo == 1) {
            mOrderSmartRefresh.finishRefresh(2000);
        } else {
            mOrderSmartRefresh.finishLoadMore(2000);
        }
    }

    @Override
    public void getOrderListFailed() {
        mOrderSmartRefresh.finishRefresh(false);
    }

    public void refresh() {
        if (App.mUser != null && !App.mUser.isLogin() && mAdapter != null) {
            mAdapter.clearData();
        } else {
            opi = new OrderPresenterImp(this, mActivity);
            opi.getOderList(1, 10,"");
        }
    }

    @Override
    public void clearData() {
        if (mAdapter != null) {
            mAdapter.clearData();
        }
    }

    @Override
    public void upData(List<OrderItem> data) {
        this.data = data;
        if (mAdapter != null){
            mAdapter.setData(data);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void clear(MessageEvent messageEvent) {
        if (AppConfig.CLEAR_DATA.equals(messageEvent.getMessage())){
            if (mAdapter != null) {
                mAdapter.clearData();
            }
        }
        if (AppConfig.UPDATA.equals(messageEvent.getMessage())){
            refresh();
        }

    }
}
