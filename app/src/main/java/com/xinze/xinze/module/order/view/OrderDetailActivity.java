package com.xinze.xinze.module.order.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vondear.rxtools.view.RxToast;
import com.xinze.xinze.R;
import com.xinze.xinze.base.BaseActivity;
import com.xinze.xinze.widget.BottomPopupMenu;
import com.xinze.xinze.module.order.modle.OrderDetail;
import com.xinze.xinze.module.order.presenter.OrderDetailPresenterImp;
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
    @BindView(R.id.from_goods_phone)
    ImageView fromGoodsPhone;
    @BindView(R.id.from_left_goods_phone)
    View fromLeftGoodsPhone;
    @BindView(R.id.to_goods_phone)
    ImageView toGoodsPhone;
    @BindView(R.id.to_left_goods_phone)
    View toLeftGoodsPhone;
    @BindView(R.id.order_wait_confirming)
    TextView orderWaitConfirming;
    @BindView(R.id.order_upload_evidence)
    TextView orderUploadEvidence;
    @BindView(R.id.upload_evidence_list)
    RecyclerView uploadEvidenceList;

    private String orderId;
    /**
     * 已接单
     */
    public final String TAKE_ORDER = "0";
    /**
     * 取货中
     */
    public final String PICK_UP = "1";
    /**
     * 发货中
     */
    public final String DELIVER_GOODS = "2";
    /**
     * 已到货
     */
    public final String GOODS_ARRIVE = "3";
    /**
     * 已签收
     */
    public final String GOODS_SIGNED_IN = "4";
    /**
     * 已拒绝
     */
    public final String GOODS_REFUSE = "B";
    /**
     * 已确定
     */
    public final String GOODS_CONFIRM = "C";
    private String phone;
    private BottomPopupMenu mBottomPopupMenu;
    private OrderDetailPresenterImp fgpi;
    private String orderStatus;
    private String remarks;

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
                switch (orderRevoke.getText().toString()) {
                    case "撤销订单":
                        showBottomMenu("确定撤销订单?",GOODS_REFUSE);
                        break;
                    case "取货":
                        showBottomMenu("确定取货?",DELIVER_GOODS);
                        break;
                    case "送达":
                        showBottomMenu("确定送达?",GOODS_ARRIVE);
                        break;
                    case "确认签收":
                        showBottomMenu("确认签收?",GOODS_SIGNED_IN);
                        break;
                    default:
                        break;
                }
            }
        });
        initTitleBar();
    }

    private void initBottomPopupMenu() {
        mBottomPopupMenu = new BottomPopupMenu(this);
        mBottomPopupMenu.addItem(1, "拍照");
        mBottomPopupMenu.addItem(2, "相册");
        mBottomPopupMenu.setOnMenuClickListener(new BottomPopupMenu.MenuClickListener() {

            @Override
            public void onMenuItemClick(View itemView, int itemId) {
                switch (itemId) {
                    // 拍照
                    case 1:

                        break;
                    // 相册
                    case 2:

                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showBottomMenu(String s,final String orderStatus) {
        mBottomPopupMenu = new BottomPopupMenu(OrderDetailActivity.this);
        mBottomPopupMenu.addItem(1, s);
        mBottomPopupMenu.addItem(2, "确定", R.color.themeOrange);
        mBottomPopupMenu.showMenu();
        mBottomPopupMenu.setOnMenuClickListener(new BottomPopupMenu.MenuClickListener() {

            @Override
            public void onMenuItemClick(View itemView, int itemId) {
                switch (itemId) {
                    // 确定撤销订单?/确定取货?/确定送货?/确定送达?
                    case 2:
                        fgpi.revoke(orderId, null, remarks, orderStatus);
                        break;
                    default:
                        break;
                }
            }
        });
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
        fgpi = new OrderDetailPresenterImp(this, this);
        fgpi.getOrderDetail(orderId);
    }

    @Override
    public void getOrderDetailSuccess(String msg) {

    }

    @Override
    public void getOrderDetailFailed(String msg) {
        RxToast.showToast(msg);
    }

    @Override
    public void revokeSuccess(String message,String orderStatus) {
        RxToast.showToast(message);
        changeState(orderStatus);
    }

    @Override
    public void revokeFailed(String message) {
        RxToast.showToast(message);
    }

    public void setData(OrderDetail data) {
        String orderID = data.getOrderid();
        orderStatus = data.getOrderstatus();

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
        remarks = data.getRemarks();


        String orderId = getString(R.string.order_id);
        findGoodsId.setText(String.format(orderId, orderID));

        findGoodsCompany.setText(companyName);

        String orderContacts = getString(R.string.order_contacts);
        findGoodsContacts.setText(String.format(orderContacts, userName));

        String orderDate = getString(R.string.order_item_date);
        findGoodsDate.setText(String.format(orderDate, dateFrom));

        String orderConsignorAndAddress = getString(R.string.order_consignor_and_address);
        findGoodsConsignor.setText(String.format(orderConsignorAndAddress, fromName, fromDetailAddress));

        String orderConsigneeAndAddress = getString(R.string.order_consignee_and_address);
        findGoodsConsignee.setText(String.format(orderConsigneeAndAddress, toName, toDetailAddress));

        String orderCarCount = getString(R.string.order_car_count);

        findGoodsCarCount.setText(Html.fromHtml(String.format(orderCarCount, truckNumber)));

        String orderGoodsDetails = getString(R.string.order_goods_details);
        findGoodsDetails.setText(Html.fromHtml(String.format(orderGoodsDetails,
                String.valueOf(msgPrice),
                String.valueOf(loadPrice),
                String.valueOf(unloadPrice),
                String.valueOf(price),
                productName,
                String.valueOf(journeyLoss),
                truckCode,
                remarks)));

        changeState(orderStatus);

    }

    private void changeState(String orderStatus) {
        if (TAKE_ORDER.equals(orderStatus)) {
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_robbing);
            setDrawable(drawable);
            fromGoodsPhone.setVisibility(View.GONE);
            fromLeftGoodsPhone.setVisibility(View.GONE);
            toGoodsPhone.setVisibility(View.GONE);
            toLeftGoodsPhone.setVisibility(View.GONE);
            orderUploadEvidence.setVisibility(View.GONE);
            uploadEvidenceList.setVisibility(View.GONE);
            orderWaitConfirming.setVisibility(View.VISIBLE);
        } else if (PICK_UP.equals(orderStatus)) {
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_picking);
            setDrawable(drawable);
            show();
            orderRevoke.setText(getString(R.string.order_pick_up_goods));
        } else if (DELIVER_GOODS.equals(orderStatus)) {
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_deliver);
            setDrawable(drawable);
            show();
            orderRevoke.setText(getString(R.string.order_deliver_goods));
        } else if (GOODS_ARRIVE.equals(orderStatus)) {
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_arrived);
            setDrawable(drawable);
            show();
            orderRevoke.setText(getString(R.string.order_confirm_arrive));
        } else if (GOODS_SIGNED_IN.equals(orderStatus)) {
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_signed);
            setDrawable(drawable);
            show();
            orderRevoke.setVisibility(View.GONE);
        } else if(GOODS_REFUSE.equals(orderStatus)){
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_refuse);
            setDrawable(drawable);
            fromGoodsPhone.setVisibility(View.GONE);
            fromLeftGoodsPhone.setVisibility(View.GONE);
            toGoodsPhone.setVisibility(View.GONE);
            toLeftGoodsPhone.setVisibility(View.GONE);
            orderUploadEvidence.setVisibility(View.GONE);
            uploadEvidenceList.setVisibility(View.GONE);
            orderWaitConfirming.setVisibility(View.GONE);
            orderRevoke.setVisibility(View.GONE);
        }else if(GOODS_CONFIRM.equals(orderStatus)){
            Drawable drawable = getResources().getDrawable(R.mipmap.goods_detail_picking);
            setDrawable(drawable);
            show();
            orderRevoke.setText(getString(R.string.order_pick_up_goods));

        }
    }

    private void show() {
        fromGoodsPhone.setVisibility(View.VISIBLE);
        fromLeftGoodsPhone.setVisibility(View.VISIBLE);
        toGoodsPhone.setVisibility(View.VISIBLE);
        toLeftGoodsPhone.setVisibility(View.VISIBLE);
        orderWaitConfirming.setVisibility(View.GONE);
        uploadEvidenceList.setVisibility(View.VISIBLE);
    }

    private void setDrawable(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        findGoodsId.setCompoundDrawables(null, null, drawable, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fgpi.onDestroy();
    }
}
