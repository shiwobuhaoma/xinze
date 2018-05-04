package com.xinze.xinze.module.order;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.module.order.modle.OrderDetail;
import com.xinze.xinze.module.order.presenter.OrderDetailPresenterImp;
import com.xinze.xinze.module.order.view.IOrderDetailView;
import com.xinze.xinze.widget.SimpleToolbar;

import java.math.BigDecimal;

import butterknife.BindView;

/**
 * @author lxf
 * 订单详情界面
 */
public class OrderDetailActivity extends BaseActivity implements IOrderDetailView {
    @BindView(R.id.find_goods_id)
    TextView findGoodsId;
    @BindView(R.id.find_goods_company)
    TextView findGoodsCompany;
    @BindView(R.id.find_goods_contacts)
    TextView findGoodsContacts;
    @BindView(R.id.find_goods_phone)
    ImageView findGoodsPhone;
    @BindView(R.id.find_goods_date)
    TextView findGoodsDate;
    @BindView(R.id.find_goods_consignor)
    TextView findGoodsConsignor;
    @BindView(R.id.find_goods_consignee)
    TextView findGoodsConsignee;
    @BindView(R.id.find_goods_car_count)
    TextView findGoodsCarCount;
    @BindView(R.id.find_goods_details)
    TextView findGoodsDetails;
    @BindView(R.id.order_revoke)
    Button orderRevoke;
    @BindView(R.id.find_tool_bar)
    SimpleToolbar findToolBar;

    private String orderId;
    /**
     * 已接单
     */
    private final String TAKE_ORDER = "0";
    /**
     * 取货中
     */
    private final String PICK_UP = "1";
    /**
     * 发货中
     */
    private final String DELIVER_GOODS = "2";
    /**
     * 已到货
     */
    private final String GOODS_ARRIVE = "3";
    /**
     * 已签收
     */
    private final String GOODS_SIGNED_IN = "4";
    private String phone;

    @Override
    protected int initLayout() {
        return R.layout.order_list_activity;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        findGoodsPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
            }
        });
        orderRevoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 撤销订单操作
            }
        });
        initTitleBar();
    }

    private void initTitleBar() {
        findToolBar.setMainTitle(R.string.order_detail);
        findToolBar.setLeftTitleDrawable(R.mipmap.ic_back);
        findToolBar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        OrderDetailPresenterImp fgpi = new OrderDetailPresenterImp(this, this);
        fgpi.getOrderDetail(orderId);
    }

    @Override
    public void getOrderDetailSuccess() {

    }

    @Override
    public void getOrderDetailFailed() {

    }

    public void setData(OrderDetail data) {
        String orderID = data.getOrderid();
        String orderStatus = data.getOrderstatus();

        String companyName = data.getCompanyname();
        String userName = data.getUsername();
        phone = data.getMobile();

        String dateFrom = data.getDateFrom();


        String fromName = data.getFromName();
        String fromDetailAddress = data.getFromDetailAdress();

        String toName = data.getToName();
        String toDetailAddress = data.getToDetailAdress();

        int truckNumber = data.getTruckNumber();

        BigDecimal msgPrice = data.getMsgPrice();
        BigDecimal loadPrice = data.getLoadPrice();
        BigDecimal unloadPrice = data.getUnloadPrice();
        BigDecimal price = data.getPrice();
        String productName = data.getProductName();
        BigDecimal journeyLoss = data.getJourneyLoss();
        String truckCode = data.getTruckCode();


        String orderId = getResources().getString(R.string.order_id);
        findGoodsId.setText(String.format(orderId,orderID));

        findGoodsCompany.setText(companyName);

        String orderContacts = getResources().getString(R.string.order_contacts);
        findGoodsContacts.setText(String.format(orderContacts,userName));

        String orderDate = getResources().getString(R.string.order_item_date);
        findGoodsDate.setText(String.format(orderDate,dateFrom));

        String orderConsignorAndAddress = getResources().getString(R.string.order_consignor_and_address);
        findGoodsConsignor.setText(String.format(orderConsignorAndAddress,fromName,fromDetailAddress));

        String orderConsigneeAndAddress = getResources().getString(R.string.order_consignee_and_address);
        findGoodsConsignee.setText(String.format(orderConsigneeAndAddress,toName,toDetailAddress));

        String orderCarCount = getResources().getString(R.string.order_car_count);

        findGoodsCarCount.setText(Html.fromHtml(String.format(orderCarCount,truckNumber)));

        String orderGoodsDetails = getResources().getString(R.string.order_goods_details);
        findGoodsDetails.setText(Html.fromHtml(String.format(orderGoodsDetails,
                String.valueOf(msgPrice),
                String.valueOf(loadPrice),
                String.valueOf(unloadPrice),
                String.valueOf(price),
                productName,
                String.valueOf(journeyLoss),
                truckCode,
                "备注")));

        if (TAKE_ORDER.equals(orderStatus)) {
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_robbing);
            setDrawable(drawable);
        }else if(PICK_UP.equals(orderStatus)){
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_picking);
            setDrawable(drawable);
        }else if(DELIVER_GOODS.equals(orderStatus)){
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_deliver);
            setDrawable(drawable);
        }else if(GOODS_ARRIVE.equals(orderStatus)){
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_arrived);
            setDrawable(drawable);
        }else if(GOODS_SIGNED_IN.equals(orderStatus)){
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_signed);
            setDrawable(drawable);
        }else {
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_refuse);
            setDrawable(drawable);
        }

    }

    private void setDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        findGoodsId.setCompoundDrawables(null, null, drawable, null);
    }


}
