package com.xinze.xinze.module.regular.view;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.xinze.xinze.module.select.view.SelectCarActivity;
import com.xinze.xinze.module.send.adapter.BillRecycleViewAdapter;
import com.xinze.xinze.module.transport.view.TransportDetailsActivity;
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
    ImageView regularLinesMiddle;
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

    protected int pageNo = 1;
    protected int pageSize = 10;
    private RegularRunPresenterImp rrp;
    private BillRecycleViewAdapter billRecycleViewAdapter;
    private List<Route> routeData;
    private String fromAreaId;
    private String toAreaId;
    private List<OrderItem> orderItemData;
    private String fromAreaName;
    private String toAreaName;
    private MenuAdapter ma;

    @Override
    protected int initLayout() {
        return R.layout.regular_run_lines_activity;
    }

    @Override
    protected void initView() {
        initToolBar();

        regularLinesLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTopMenu();
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ma = new MenuAdapter(this);
        regularLinesRv.setLayoutManager(llm);
        regularLinesRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        regularLinesRv.setAdapter(ma);
        ma.setOnItemClickListener(new MenuAdapter.MenuClickListener() {
            @Override
            public void onMenuItemClick(View itemView, int position) {
                ma.clearMenu();
                regularLinesFl.setVisibility(View.GONE);
                fromAreaId = routeData.get(position).getFromAreaId();
                toAreaId = routeData.get(position).getToAreaId();
                fromAreaName = routeData.get(position).getFrom_area_name();
                toAreaName = routeData.get(position).getTo_area_name();
                rrp.searchRouteList(fromAreaId, toAreaId, pageNo, pageSize);
                if ("0".equals(fromAreaId)) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) regularLinesStart.getLayoutParams();
                    layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    layoutParams.weight = 0;
                    regularLinesStart.setLayoutParams(layoutParams);
                    regularLinesStart.setText("全部");
                    regularLinesStart.setTextColor(getResources().getColor(R.color.themeOrange));
                    Drawable mDrawable = getResources().getDrawable(R.mipmap.ruglar_line_down);
                    mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
                    regularLinesStart.setCompoundDrawables(null, null, mDrawable, null);
                    regularLinesMiddle.setVisibility(View.GONE);
                    regularLinesEnd.setVisibility(View.GONE);
                } else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) regularLinesStart.getLayoutParams();
                    layoutParams.weight = 1;
                    layoutParams.width = 0;
                    regularLinesStart.setLayoutParams(layoutParams);
                    regularLinesStart.setText(fromAreaName);
                    regularLinesStart.setCompoundDrawables(null, null, null, null);
                    regularLinesStart.setTextColor(getResources().getColor(R.color.themeBlack));
                    regularLinesStart.setVisibility(View.VISIBLE);

                    regularLinesMiddle.setVisibility(View.VISIBLE);

                    regularLinesEnd.setText(toAreaName);
                    Drawable mDrawable = getResources().getDrawable(R.mipmap.ruglar_line_up);
                    mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
                    regularLinesEnd.setCompoundDrawables(null, null, mDrawable, null);
                    regularLinesEnd.setVisibility(View.VISIBLE);
                }

            }
        });


        regularOrdersRv.setLayoutManager(new LinearLayoutManager(this));
        billRecycleViewAdapter = new BillRecycleViewAdapter(this,"RegularRunActivity");

        regularOrdersRv.setAdapter(billRecycleViewAdapter);
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
            public void jumpSelectCar( int position) {

                jumpToSelectCarActivity(position);
            }

            @Override
            public void jumpDetails(int position) {
                jumpToOrderDetailActivity(position);
            }
        });
    }

    private void jumpToSelectCarActivity(int position) {
        OrderItem orderItem = orderItemData.get(position);
        String orderId = orderItem.getId();
        openActivity(SelectCarActivity.class,"orderId", orderId);
    }

    private void showTopMenu() {
        if (regularLinesFl.getVisibility() == View.GONE){
            regularLinesFl.setVisibility(View.VISIBLE);
            if (routeData != null) {
                for (int i = 0; i < routeData.size(); i++) {
                    Route route = routeData.get(i);
                    ma.addItem(i + 1, route.getFrom_area_name(), route.getTo_area_name(), Integer.valueOf(route.getFromAreaId()) == 0);
                }
                ma.notifyDataSetChanged();

            }
        }else{
            regularLinesFl.setVisibility(View.GONE);
        }



    }


    private void jumpToOrderDetailActivity(int position) {
        OrderItem orderItem = orderItemData.get(position);
        String orderId = orderItem.getId();
        openActivity(TransportDetailsActivity.class, "orderId", orderId);
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
        if (pageNo == 1) {
            regularLinesSrl.finishRefresh();
        } else {
            regularLinesSrl.finishLoadMore();
        }

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
        billRecycleViewAdapter.setData(orderItemData);
        regularLinesSrl.setVisibility(View.VISIBLE);
    }

    public void setRouteData(List<Route> data) {
        this.routeData = data;
    }
}
