package com.xinze.xinze.module.select.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.MainConfig;
import com.xinze.xinze.config.ProtocolConfig;
import com.xinze.xinze.module.about.view.AboutUsActivity;
import com.xinze.xinze.module.allot.view.AllotDriverActivity;
import com.xinze.xinze.module.main.activity.MainActivity;
import com.xinze.xinze.module.select.adapter.SelectCarAdapter;
import com.xinze.xinze.module.select.module.Protocol;
import com.xinze.xinze.module.select.presenter.SelectCarPresenterImp;
import com.xinze.xinze.module.transport.module.Car;
import com.xinze.xinze.utils.DialogUtil;
import com.xinze.xinze.utils.DividerItemDecoration;
import com.xinze.xinze.utils.MessageEvent;
import com.xinze.xinze.widget.SimpleToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.select_service_iv)
    ImageView selectServiceIv;
    @BindView(R.id.select_confirm_bill)
    Button selectConfirmBill;
    private SelectCarAdapter mAdapter;
    /**
     * 是否全部选中
     */
    private boolean isAllSelected;
    private boolean isProtocolSelected = true;
    private SelectCarPresenterImp scp;
    private String billId;
    private List<Car> carList;
    private List<Car> selectCarList = new ArrayList<>();
    private List<Car> selectedCarList = new ArrayList<>();
    private String from;
    /**
     * adapter中选中的个数
     */
    private int adapterSelectedSize = -1;
    /**
     * 从网络获取数据中可以选择的个数
     */
    private int selectSize = 0;


    @Override
    protected int initLayout() {
        return R.layout.select_car_activity;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        billId = intent.getStringExtra("orderId");
        from = intent.getStringExtra("from");
        if ("OrdinaryBillFragment".equals(from)) {
            selectConfirmBill.setText(getString(R.string.select_receipt_bill));
        } else {
            selectConfirmBill.setText(getString(R.string.select_confirm_bill));
        }
        initToolbar();
        selectService.setText(Html.fromHtml(getString(R.string.select_read_service)));

        mAdapter = new SelectCarAdapter(this);
        selectRv.setLayoutManager(new LinearLayoutManager(this));
        selectRv.addItemDecoration(new DividerItemDecoration(this));
        selectRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SelectCarAdapter.OnItemClickListener() {
            @Override
            public void click(View view, int position) {

                Car car = carList.get(position);
                //0表示自有 1表示关联
                String ownFlag = car.getOwnFlag();
                String reason = car.getReason();
                if ("0".equals(ownFlag)) {
                    //审核状态 1表示已审核 0 审核失败 2 审核中
                    if ("2".equals(car.getVertify_flag())) {
                        DialogUtil.showCommonDialog(SelectCarActivity.this, "车辆正在审核中...", "知道了");
                        return;
                    } else if ("1".equals(car.getVertify_flag())) {
                        //审核已通过，司机未关联
                        if (TextUtils.isEmpty(car.getDriver_id())) {
                            //跳转到分配司机界面
                            Intent intent = new Intent(SelectCarActivity.this, AllotDriverActivity.class);
                            intent.putExtra("truckId", car.getId());
                            intent.putExtra("driverId", car.getDriver_id());
                            DialogUtil.showCommonDialog(SelectCarActivity.this, "车辆未关联司机", intent, "去关联");
                            return;
                        }
                    } else {
                        DialogUtil.showCommonDialog(SelectCarActivity.this, reason, "知道了");
                        return;
                    }
                } else {
                    String rightFlag = car.getRight_flag();
                    if ("0".equals(rightFlag)) {
                        DialogUtil.showCommonDialog(SelectCarActivity.this, "没有权限抢单", "知道了");
                        return;
                    }
                }

                mAdapter.updateState(position);

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
//        shotToast(msg);
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
        shotToast("抢单成功");
//        openActivity(TransportDetailsActivity.class, "orderId", billId);
        MainActivity.currentFragment = MainConfig.ORDER_FRAGMENT;
        openActivity(MainActivity.class);
    }

    @Override
    public void createBillOrderFailed(String msg) {
        shotToast(msg);
    }

    public void setData(List<Car> data) {
        this.carList = data;

        for (Car car : carList) {
            if (car.isSelected()) {
                if (!selectedCarList.contains(car)) {
                    selectedCarList.add(car);
                }
            }
        }
        selectSize = selectedCarList.size();
        selectedCarList.clear();

        if (carList == null || carList.size() == 0) {
            Drawable drawable = getResources().getDrawable(R.mipmap.select_choice);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            selectAll.setCompoundDrawables(drawable, null, null, null);
            selectTop.setOnClickListener(null);
        } else {
            selectTop.setOnClickListener(this);
        }
        mAdapter.setData(data);
    }

    @Override
    @OnClick({R.id.select_top, R.id.select_service, R.id.select_confirm_bill, R.id.select_service_iv})
    public void onClick(View view) {
        Drawable drawable;
        switch (view.getId()) {
            case R.id.select_top:
                if (isAllSelected) {
                    drawable = getResources().getDrawable(R.mipmap.select_choice);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_gray_button));
                    selectConfirmBill.setClickable(false);
                    mAdapter.updateAll(false);
                } else {
                    selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_orange_button));
                    selectConfirmBill.setClickable(true);
                    drawable = getResources().getDrawable(R.mipmap.select_choicd);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    mAdapter.updateAll(true);

                    for (Car car : carList) {
                        if (car.isSelected()) {
                            if (!selectedCarList.contains(car)) {
                                selectedCarList.add(car);
                            }
                        }
                    }
                    adapterSelectedSize = selectedCarList.size();
                    selectedCarList.clear();
                }
                isAllSelected = !isAllSelected;
                selectAll.setCompoundDrawables(drawable, null, null, null);
                isProtocolSelected = true;
                selectServiceIv.setBackground(getResources().getDrawable(R.mipmap.my_driver_selected));
                break;
            case R.id.select_service:
                openActivity(AboutUsActivity.class, "type", ProtocolConfig.TRANSPORT_SERVICE_PROTOCOL);

//                scp.getProtocolByType(ProtocolConfig.TRANSPORT_SERVICE_PROTOCOL);

                break;
            case R.id.select_service_iv:
                isProtocolSelected = !isProtocolSelected;
                if (isProtocolSelected) {
                    selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_orange_button));
                    selectConfirmBill.setClickable(true);
                    selectServiceIv.setBackground(getResources().getDrawable(R.mipmap.my_driver_selected));
                } else {
                    selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_gray_button));
                    selectConfirmBill.setClickable(false);
                    selectServiceIv.setBackground(getResources().getDrawable(R.mipmap.my_driver_unselected));
                }

                break;
            case R.id.select_confirm_bill:
                if (isProtocolSelected) {
                    if (isAllSelected) {
                        for (Car car : carList) {
                            if (car.isSelected()) {
                                selectCarList.add(car);
                            }
                        }
                        selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_orange_button));
                        selectConfirmBill.setClickable(true);
                        scp.createBillOrder(billId, selectCarList);
                    } else {
                        if (carList == null || carList.size() == 0) {
                            selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_gray_button));
                            selectConfirmBill.setClickable(false);
                            return;
                        }
                        for (Car car : carList) {
                            if (car.isSelected()) {
                                selectCarList.add(car);
                            }
                        }
                        if (selectCarList.size() == 0) {
                            return;
                        }
                        scp.createBillOrder(billId, selectCarList);
                    }
                } else {
                    shotToast("请勾选运输服务合同");
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isSelect(MessageEvent messageEvent) {
        String message = messageEvent.getMessage();
        if ("select".equals(message)) {
            if (isProtocolSelected) {
                selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_orange_button));
                selectConfirmBill.setClickable(true);
            } else {
                selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_gray_button));
                selectConfirmBill.setClickable(false);
            }
            
            List<Car> data = mAdapter.getData();
            for (Car c : data) {
                if (c.isSelected()) {
                    if (!selectedCarList.contains(c)) {
                        selectedCarList.add(c);
                    }
                }
            }
            if (adapterSelectedSize == selectedCarList.size()) {
                Drawable drawable = getResources().getDrawable(R.mipmap.select_choicd);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                selectAll.setCompoundDrawables(drawable, null, null, null);
                isAllSelected = true;
            }
        } else {
           
            selectedCarList.clear();
            List<Car> data = mAdapter.getData();
            for (Car c : data) {
                if (c.isSelected()) {
                    if (!selectedCarList.contains(c)) {
                        selectedCarList.add(c);
                    }
                }
            }

            if (selectedCarList.size() < adapterSelectedSize) {
                Drawable drawable = getResources().getDrawable(R.mipmap.select_choice);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                selectAll.setCompoundDrawables(drawable, null, null, null);
                isAllSelected = false;
            } else if (adapterSelectedSize == selectedCarList.size()) {
                Drawable drawable = getResources().getDrawable(R.mipmap.select_choicd);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                selectAll.setCompoundDrawables(drawable, null, null, null);
                isAllSelected = true;
            }

            if (selectedCarList.size() > 0) {
                selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_orange_button));
                selectConfirmBill.setClickable(true);
            } else {
                selectConfirmBill.setBackground(getResources().getDrawable(R.drawable.circle_gray_button));
                selectConfirmBill.setClickable(false);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
