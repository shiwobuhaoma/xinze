package com.xinze.xinze.module.transport.view;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.App;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.config.MainConfig;
import com.xinze.xinze.module.main.activity.MainActivity;
import com.xinze.xinze.module.select.view.SelectCarActivity;
import com.xinze.xinze.module.transport.module.TransportDetails;
import com.xinze.xinze.module.transport.presenter.TransportDetailsPresenterImp;
import com.xinze.xinze.module.transport.view.ITransportDetailsView;
import com.xinze.xinze.widget.BottomPopupMenu;
import com.xinze.xinze.widget.SimpleToolbar;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 货运详情
 *
 * @author lxf
 */
public class TransportDetailsActivity extends BaseActivity implements ITransportDetailsView, View.OnClickListener {
    @BindView(R.id.transport_tool_bar)
    SimpleToolbar transportToolBar;
    @BindView(R.id.transport_goods_company)
    TextView transportGoodsCompany;
    @BindView(R.id.transport_goods_contacts)
    TextView transportGoodsContacts;
    @BindView(R.id.transport_goods_phone)
    ImageView transportGoodsPhone;
    @BindView(R.id.transport_goods_date)
    TextView transportGoodsDate;
    @BindView(R.id.transport_goods_consignor)
    TextView transportGoodsConsignor;
    @BindView(R.id.from_transport_phone)
    ImageView fromTransportPhone;
    @BindView(R.id.find_transport_consignee)
    TextView findTransportConsignee;
    @BindView(R.id.to_transport_phone)
    ImageView toTransportPhone;
    @BindView(R.id.transport_goods_car_count)
    TextView transportGoodsCarCount;
    @BindView(R.id.transport_goods_details)
    TextView transportGoodsDetails;
    @BindView(R.id.transport_refuse)
    Button transportRefuse;
    @BindView(R.id.transport_confirming)
    Button mTransportConfirming;
    private String phone;
    private TransportDetailsPresenterImp tap;
    private String orderId;

    @Override
    protected int initLayout() {
        return R.layout.transport_details_activity;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        initToolbar();
        tap = new TransportDetailsPresenterImp(this,this);
        tap.getBillDetail(orderId);
        transportRefuse.setOnClickListener(this);
        mTransportConfirming.setOnClickListener(this);
    }

    private void initToolbar() {
        transportToolBar.setLeftTitleVisible();
        transportToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        transportToolBar.setMainTitle("货运详情");
    }
    @Override
    @OnClick({R.id.transport_goods_phone, R.id.from_transport_phone, R.id.to_transport_phone, R.id.transport_confirming})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.transport_goods_phone:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
                break;
            case R.id.from_transport_phone:
                break;
            case R.id.to_transport_phone:

                break;
            case R.id.transport_refuse:
                showBottomMenu();

                break;
            case R.id.transport_confirming:
                tap.getCarryOrderRight(App.mUser.getId());


                break;
            default:
                break;
        }
    }

    private void showBottomMenu() {
        BottomPopupMenu bottomPopupMenu = new BottomPopupMenu(this);
        bottomPopupMenu.addItem(1,"退订后，您将无法再接此单，确定退单吗？",getResources().getColor(R.color.gray));
        bottomPopupMenu.addItem(2,"确定",getResources().getColor(R.color.themeOrange));
        bottomPopupMenu.setOnMenuClickListener(new BottomPopupMenu.MenuClickListener() {
            @Override
            public void onMenuItemClick(View itemView, int itemId) {
                if (itemId == 2){
                    tap.backBill(orderId);
                }
            }
        });
        bottomPopupMenu.showMenu();
    }

    @Override
    public void getBillDetailSuccess(String msg) {
//        RxToast.showToast(msg);
    }

    @Override
    public void getBillDetailFailed(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void getCarryOrderRightSuccess(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void getCarryOrderRightFailed(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void backBillSuccess(String msg) {
        RxToast.showToast(msg);
        finish();
    }

    @Override
    public void backBillFailed(String msg) {
        RxToast.showToast(msg);
    }

    public void setData(TransportDetails data) {
        TransportDetails.DetailBean detail = data.getDetail();
        TransportDetails.OwnerBean owner = data.getOwner();
        String companyName = owner.getCompanyname();
        String userName = owner.getName();
        phone = owner.getMobile();

        String dateFrom = detail.getDateFrom();


        String fromName = detail.getFromName();
        String fromDetailAddress = detail.getFromDetailAdress();

        String toName = detail.getToName();
        String toDetailAddress = detail.getToDetailAdress();

        int truckNumber = detail.getTruckNumber();

        BigDecimal msgPrice = detail.getMsgPrice();
        BigDecimal loadPrice = detail.getLoadPrice();
        BigDecimal unloadPrice = detail.getUnloadPrice();
        BigDecimal price = detail.getPrice();
        String productName = detail.getProductName();
        BigDecimal journeyLoss = detail.getJourneyLoss();
        String truckCode = detail.getTruckCode();
        String remarks = detail.getRemarks();
        int leftNumber = detail.getLeft_number();


        transportGoodsCompany.setText(companyName);

        String orderContacts = getString(R.string.order_contacts);
        transportGoodsContacts.setText(String.format(orderContacts, userName));

        String orderDate = getString(R.string.order_item_date);
        transportGoodsDate.setText(String.format(orderDate, dateFrom));

        String orderConsignorAndAddress = getString(R.string.order_consignor_and_address);
        transportGoodsConsignor.setText(String.format(orderConsignorAndAddress, fromName, fromDetailAddress));

        String orderConsigneeAndAddress = getString(R.string.order_consignee_and_address);
        findTransportConsignee.setText(String.format(orderConsigneeAndAddress, toName, toDetailAddress));

        String orderCarCount = getString(R.string.order_car_count);

        transportGoodsCarCount.setText(Html.fromHtml(String.format(orderCarCount, truckNumber)));

        String orderGoodsDetails = getString(R.string.order_goods_details);
        transportGoodsDetails.setText(Html.fromHtml(String.format(orderGoodsDetails,
                String.valueOf(msgPrice),
                String.valueOf(loadPrice),
                String.valueOf(unloadPrice),
                String.valueOf(price),
                productName,
                String.valueOf(journeyLoss),
                truckCode,
                remarks)));
        String transportConfirming = getString(R.string.transport_confirming);
        mTransportConfirming.setText(String.format(transportConfirming,leftNumber));

    }

    public void isCarry(Integer data) {
        if (data == 0){
            //TODO 不允许抢单
        }else{
        MainActivity.currentFragment = MainConfig.ORDER_FRAGMENT;
        openActivity(MainActivity.class);
//            openActivity(SelectCarActivity.class,"orderId",orderId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tap != null){
            tap.onDestroy();
        }
    }
}
