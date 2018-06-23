package com.xinze.xinze.module.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinze.xinze.R;
import com.xinze.xinze.module.main.modle.OrderItem;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lxf
 * 订单界面适配器
 */
public class OrderRecycleViewAdapter extends RecyclerView.Adapter<OrderRecycleViewAdapter.ViewHolder> implements View.OnClickListener {
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
    /**
     * 已拒绝
     */
    private final String GOODS_REFUSE = "R";
    /**
     * 已撤销
     */
    private final String GOODS_REVOKE = "B";
    /**
     * 已过期
     */
    private final String GOODS_OVERDUE = "X";
    /**
     * 已确定
     */
    private final String GOODS_CONFIRM = "Count";
    private List<OrderItem> mBS;
    private Context mContext;
    private View view;

    public OrderRecycleViewAdapter(Context context, List<OrderItem> mbs) {
        this.mContext = context;
        this.mBS = mbs;
    }

    public OrderRecycleViewAdapter(Context context) {
        this.mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(
                mContext).inflate(R.layout.order_rv_item, parent,
                false);
        ViewHolder holder = new ViewHolder(view);

        //给布局设置点击和长点击监听
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        OrderItem orderRecycleViewItem = mBS.get(position);

        String startText = orderRecycleViewItem.getFromDetailAdress();
        String endText = orderRecycleViewItem.getToDetailAdress();
        String id = orderRecycleViewItem.getOrderid();
        String productName = orderRecycleViewItem.getProductName();
        String truckCode = orderRecycleViewItem.getTruckCode();
        BigDecimal distance = orderRecycleViewItem.getDistance();
        BigDecimal freight = orderRecycleViewItem.getPrice();

        String orderId = mContext.getResources().getString(R.string.order_id);
        String money = mContext.getResources().getString(R.string.order_freight);
        String type = mContext.getResources().getString(R.string.order_truck_type);
        String product = mContext.getResources().getString(R.string.order_product_name);
        String distances = mContext.getResources().getString(R.string.order_distance);


        id = getString(id, orderId);
        money = getString(String.valueOf(freight), money);
        type = getString(truckCode, type);
        product = getString(productName, product);
        distances = getString(String.valueOf(distance), distances);

        viewHolder.orderItemTvId.setText(id);
        viewHolder.orderTvFreight.setText(money);
        viewHolder.orderTvTruckCode.setText(type);
        viewHolder.orderTvProductName.setText(product);
        viewHolder.orderTvDistance.setText(distances);

        if (!TextUtils.isEmpty(startText)) {
            viewHolder.homeTvRightStart.setText(startText);
        }
        if (!TextUtils.isEmpty(endText)) {
            viewHolder.homeTvRightEnd.setText(endText);
        }

        String icon = orderRecycleViewItem.getOrderstatus();
        if (!TextUtils.isEmpty(icon)) {
            if (TAKE_ORDER.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_robbing_order));
            } else if (PICK_UP.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_pick_up));
            } else if (DELIVER_GOODS.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_deliver));
            } else if (GOODS_ARRIVE.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_arrive));
            } else if (GOODS_SIGNED_IN.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_signed_in));
            } else if (GOODS_REFUSE.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_refuse));
            } else if (GOODS_REVOKE.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_revoke));
            } else if (GOODS_CONFIRM.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_robbing_order));
            }else if (GOODS_OVERDUE.equals(icon)) {
                viewHolder.homeIvState.setBackground(mContext.getResources().getDrawable(R.mipmap.goods_overdue));
            }

        }

        if (position == mBS.size() - 1) {
            viewHolder.orderItemVSpace.setVisibility(View.GONE);
        } else {
            viewHolder.orderItemVSpace.setVisibility(View.VISIBLE);
        }

        //将position保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);
    }

    private String getString(String id, String orderId) {
        id = String.format(orderId, id);
        return id;
    }

    public void clearData() {
        if(mBS != null){
            mBS.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mBS == null ? 0 :mBS.size();
    }


    //点击事件回调
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setData(List<OrderItem> data) {
        this.mBS = data;
        notifyDataSetChanged();
    }

    //自定义监听事件
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.order_item_tv_id)
        TextView orderItemTvId;
        @BindView(R.id.order_item_tv_line)
        View orderItemTvLine;
        @BindView(R.id.order_tv_right_start)
        TextView homeTvRightStart;
        @BindView(R.id.order_tv_right_end)
        TextView homeTvRightEnd;
        @BindView(R.id.order_iv_state)
        ImageView homeIvState;
        @BindView(R.id.order_item_rl)
        RelativeLayout orderItemRl;
        @BindView(R.id.order_item_v_line)
        View orderItemVLine;
        @BindView(R.id.order_tv_freight)
        TextView orderTvFreight;
        @BindView(R.id.order_tv_truckCode)
        TextView orderTvTruckCode;
        @BindView(R.id.order_tv_productName)
        TextView orderTvProductName;
        @BindView(R.id.order_tv_distance)
        TextView orderTvDistance;
        @BindView(R.id.order_item_v_space)
        View orderItemVSpace;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
