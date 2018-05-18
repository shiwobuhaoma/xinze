package com.xinze.xinze.module.select.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.MainConfig;
import com.xinze.xinze.config.ProtocolConfig;
import com.xinze.xinze.module.add.AddMyCarActivity;
import com.xinze.xinze.module.main.activity.MainActivity;
import com.xinze.xinze.module.select.adapter.SelectCarAdapter;
import com.xinze.xinze.module.select.module.Protocol;
import com.xinze.xinze.module.select.presenter.SelectCarPresenterImp;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.utils.DialogUtil;
import com.xinze.xinze.utils.DividerItemDecoration;
import com.xinze.xinze.widget.SimpleToolbar;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * 选择车辆
 *
 * @author lxf
 */
public class SelectCarActivity extends BaseActivity implements ISelectCarView, View.OnClickListener {


    @BindView(R.id.select_tool_bar)
    SimpleToolbar selectToolBar;
    @BindView(R.id.select_top)
    LinearLayout selectTop;
    @BindView(R.id.select_tv_all)
    TextView selectAll;
    @BindView(R.id.select_rv)
    RecyclerView selectRv;
    @BindView(R.id.select_service)
    TextView selectService;
    @BindView(R.id.select_confirm_bill)
    Button selectConfirmBill;
    private SelectCarAdapter sca;
    /**
     * 是否全部选中
     */
    private boolean isAllSelected;
    private SelectCarPresenterImp scp;
    private String billId;
    private List<Car> carList;
    private List<Car> selectCarList = new ArrayList<>();


    @Override
    protected int initLayout() {
        return R.layout.select_car_activity;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        billId = intent.getStringExtra("orderId");
        initToolbar();
        selectTop.setOnClickListener(this);
        selectService.setText(Html.fromHtml(getString(R.string.select_read_service)));

        sca = new SelectCarAdapter(this);
        selectRv.setLayoutManager(new LinearLayoutManager(this));
        selectRv.addItemDecoration(new DividerItemDecoration(this));
        selectRv.setAdapter(sca);
        sca.setOnItemClickListener(new SelectCarAdapter.OnItemClickListener() {
            @Override
            public void click(View view, int position) {
                sca.updateState(position);
            }
        });
    }

    private void initToolbar() {
        selectToolBar.setMainTitle("选择车辆");
        selectToolBar.setLeftTitleVisible();
        selectToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        scp = new SelectCarPresenterImp(this, this);
        scp.getCarryTruckList(App.mUser.getId());
    }

    @Override
    public void getCarryTruckListSuccess(String msg) {
        shotToast(msg);
    }

    @Override
    public void getCarryTruckListFailed(String msg) {
        shotToast(msg);
    }

    @Override
    public void getProtocolByTypeSuccess(String msg) {
        shotToast(msg);
    }

    @Override
    public void getProtocolByTypeFailed(String msg) {
        shotToast(msg);
    }

    @Override
    public void createBillOrderSuccess(String msg) {
        shotToast(msg);
        MainActivity.currentFragment = MainConfig.ORDER_FRAGMENT;
        openActivity(MainActivity.class);
    }

    @Override
    public void createBillOrderFailed(String msg) {
        shotToast(msg);
    }

    public void setData(List<Car> data) {
        this.carList = data;
        sca.setData(data);
    }

    @Override
    @OnClick({R.id.select_top, R.id.select_service, R.id.select_confirm_bill})
    public void onClick(View view) {
        Drawable drawable;
        switch (view.getId()) {
            case R.id.select_top:
                if (isAllSelected) {
                    drawable = getResources().getDrawable(R.mipmap.select_choice);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    sca.updateAll(false);
                } else {
                    drawable = getResources().getDrawable(R.mipmap.select_choicd);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    sca.updateAll(true);
                }
                isAllSelected = !isAllSelected;
                selectAll.setCompoundDrawables(drawable, null, null, null);

                break;
            case R.id.select_service:
                scp.getProtocolByType(ProtocolConfig.TRANSPORT_SERVICE_PROTOCOL);
                break;
            case R.id.select_confirm_bill:

                if (isAllSelected) {
                    scp.createBillOrder(billId, carList);
                } else {
                    for (Car car : carList) {
                        if (car.isSelected()) {
                            selectCarList.add(car);
                        }
                    }
                    scp.createBillOrder(billId, selectCarList);
                }

                break;
            default:
                break;
        }
    }

    public void setProtocolData(Protocol data) {
        String protocolContext = data.getContent();
        String protocolName = data.getProtocolName();
    }
}
