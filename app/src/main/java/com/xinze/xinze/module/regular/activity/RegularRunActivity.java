package com.xinze.xinze.module.regular.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.main.modle.OrderItem;
import com.xinze.xinze.module.regular.adapter.MenuAdapter;
import com.xinze.xinze.module.regular.modle.Route;
import com.xinze.xinze.module.regular.presenter.RegularRunPresenterImp;
import com.xinze.xinze.module.regular.view.IRegularRouteView;
import com.xinze.xinze.module.send.adapter.BillRecycleViewAdapter;
import com.xinze.xinze.widget.SimpleToolbar;

import java.util.List;

import butterknife.BindView;

/**
 * @author lxf
 * 常跑路线
 */
public class RegularRunActivity extends BaseActivity implements IRegularRouteView {
    @BindView(R.id.regular_tool_bar)
    SimpleToolbar regularToolBar;
    @BindView(R.id.regular_lines_start)
    TextView regularLinesStart;
    @BindView(R.id.regular_lines_middle)
    TextView regularLinesMiddle;
    @BindView(R.id.regular_lines_end)
    TextView regularLinesEnd;
    @BindView(R.id.regular_lines_ll)
    LinearLayout regularLinesLl;
    @BindView(R.id.regular_lines_rv)
    RecyclerView regularLinesRv;
    @BindView(R.id.regular_lines_srl)
    SmartRefreshLayout regularLinesSrl;
    @BindView(R.id.regular_lines_fl)
    FrameLayout regularLinesFl;
    @BindView(R.id.regular_orders_rv)
    RecyclerView regularOrdersRv;
    private LinearLayoutManager llm;
    protected int pageNo = 1;
    protected int pageSize = 10;
    private RegularRunPresenterImp rrp;
    private BillRecycleViewAdapter billRecycleViewAdapter;
    private List<Route> routeData;
    private String fromAreaId;
    private String toAreaId;
    private List<OrderItem> orderItemData;
    private String from_area_name;
    private String to_area_name;

    @Override
    protected int initLayout() {
        return R.layout.regular_run_lines_activity;
    }

    @Override
    protected void initView() {
        initToolBar();
        llm = new LinearLayoutManager(this);
        regularLinesRv.setLayoutManager(llm);
        billRecycleViewAdapter = new BillRecycleViewAdapter(this);
        regularLinesRv.setAdapter(billRecycleViewAdapter);
        regularLinesSrl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 1;
                rrp.searchRouteList(fromAreaId, toAreaId, pageNo, pageSize);
            }
        });
        regularLinesSrl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                rrp.searchRouteList(fromAreaId, toAreaId, pageNo, pageSize);
            }
        });
        billRecycleViewAdapter.setOnItemClickListener(new BillRecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpToOrderDetailActivity(position);
            }
        });
        regularLinesLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopMenu();
            }
        });


    }

    private void showTopMenu() {
        regularLinesFl.setVisibility(View.VISIBLE);
        Drawable mDrawable = getResources().getDrawable(R.mipmap.ruglar_line_up);
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        regularLinesStart.setCompoundDrawables(null, null, mDrawable, null);
        MenuAdapter ma = new MenuAdapter(this);
        regularOrdersRv.setLayoutManager(new LinearLayoutManager(this));
        regularOrdersRv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        regularOrdersRv.setAdapter(ma);
        if (routeData != null) {
            for (int i = 0; i < routeData.size(); i++) {
                Route route = routeData.get(i);
                ma.addItem(i + 1, route.getFrom_area_name(), route.getTo_area_name(), Integer.valueOf(route.getFromAreaId()) == 0);
            }
            ma.notifyDataSetChanged();
            ma.setOnItemClickListener(new MenuAdapter.MenuClickListener() {
                @Override
                public void onMenuItemClick(View itemView, int position) {
                    fromAreaId = routeData.get(position).getFromAreaId();
                    toAreaId = routeData.get(position).getToAreaId();
                    from_area_name = routeData.get(position).getFrom_area_name();
                    to_area_name = routeData.get(position).getTo_area_name();
                    rrp.searchRouteList(fromAreaId, toAreaId, pageNo, pageSize);
                    if ("0".equals(fromAreaId)) {
                        regularLinesStart.setText("全部");
                        regularLinesStart.setTextColor(getResources().getColor(R.color.themeOrange));
                        Drawable mDrawable = getResources().getDrawable(R.mipmap.ruglar_line_down);
                        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
                        regularLinesStart.setCompoundDrawables(null, null, mDrawable, null);
                        regularLinesMiddle.setVisibility(View.GONE);
                        regularLinesEnd.setVisibility(View.GONE);
                    } else {
                        regularLinesStart.setText(from_area_name);
                        regularLinesStart.setTextColor(getResources().getColor(R.color.themeBlack));
                        regularLinesStart.setVisibility(View.VISIBLE);
                        regularLinesEnd.setText(to_area_name);
                        regularLinesEnd.setVisibility(View.VISIBLE);
                    }
                    regularLinesFl.setVisibility(View.GONE);
                }
            });
        }
    }

    private void jumpToOrderDetailActivity(int position) {
        Route route = routeData.get(position);
        String orderId = route.getId();
        openActivity(RegularRunActivity.class, "orderId", orderId);
    }

    private void initToolBar() {
        regularToolBar.setLeftTitleVisible();
        regularToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        regularToolBar.setMainTitle("常跑路线");
    }

    @Override
    protected void initData() {
        super.initData();
        rrp = new RegularRunPresenterImp(this, this);
        rrp.getRegularRouteList();
    }

    @Override
    public void getRegularRouteListSuccess(String msg) {
        shotToast(msg);
    }

    @Override
    public void getRegularRouteListFailed(String msg) {
        shotToast(msg);
    }

    @Override
    public void searchRouteListSuccess(String msg) {
        shotToast(msg);
    }

    @Override
    public void searchRouteListFailed(String msg) {
        shotToast(msg);
    }

    public void setOrderItemData(List<OrderItem> data) {
        if (pageNo == 1) {
            this.orderItemData = data;
        } else {
            this.orderItemData.addAll(data);
        }
        billRecycleViewAdapter.setData(data);
    }

    public void setRouteData(List<Route> data) {
        this.routeData = data;

    }
}
